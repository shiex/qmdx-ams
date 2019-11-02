package com.qmdx.ams.controller;

import com.qmdx.ams.bo.FileUploadBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.excel.ExcelCompanyPosition;
import com.qmdx.ams.excel.ExcelOperation;
import com.qmdx.ams.excel.ExcelParsingData;
import com.qmdx.ams.excel.ExcelStudentAdvisor;
import com.qmdx.ams.service.*;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/auth/staff")
public class ExcelController {

    @Resource
    private ExcelService excelService;

    private final String suffixType = "xls,xlsx";

    /**
     * upload table
     *
     * @param multipartFile
     * @param request
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping(value = "/upload/file",method = RequestMethod.POST)
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile multipartFile,
                                          HttpServletRequest request) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        String excelType = request.getParameter("excelType");
        if(multipartFile != null) {
            FileUploadBO fileUploadBO = new FileUploadBO(multipartFile.getOriginalFilename());

            if(!suffixType.contains(fileUploadBO.getSuffix())){
                RestMapBO.setErrorRestMap(restMap, "upload only xls/xlsx file");
                return restMap;
            }

            try{
                List excelDataList = ExcelOperation.read(excelType, multipartFile.getInputStream());
                if (excelDataList.size() > 0) {
                    if (excelDataList.get(0).getClass() == ExcelStudentAdvisor.class) {
                        excelService.insertStudentAdvisor(excelDataList);
                    } else {
                        excelService.insertCompanyPosition(excelDataList);
                    }
                }
            }catch (IOException e){
                RestMapBO.setErrorRestMap(restMap, e.getMessage());
            }
        }
        return restMap;
    }

    /**
     * derive table
     *
     * @param type
     * @param response
     * @return: void
     */
    @RequestMapping("/derive/excel/{type}")
    public void deriveExcel(@PathVariable("type") String type, HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            // 下载表格
            String excelName = null;
            if (StringUtils.equals(type, "studentInfo")) excelName = "studentInfo.xlsx";
            if (StringUtils.equals(type, "companyInfo")) excelName = "companyInfo.xlsx";
            String classPath = ResourceUtils.getURL("classpath:").getPath().substring(1);
            File excelFile = new File(classPath, "/static/asserts/excel/" + excelName);

            response.setHeader("Content-length", String.valueOf(excelFile.length()));
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel");

            inputStream = new FileInputStream(excelFile);
            outputStream = response.getOutputStream();
            streamCopy(inputStream, outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outputStream.close();
            }catch (Exception e){}
        }
    }

    private long streamCopy(InputStream in, OutputStream out) throws IOException{
        long count = 0;
        byte[] buf = new byte[1024];
        while (true){
            int n = in.read(buf);
            if(n < 0) break;
            if(n == 0) continue;
            out.write(buf, 0, n);
            count += n;
        }
        return count;
    }
}
