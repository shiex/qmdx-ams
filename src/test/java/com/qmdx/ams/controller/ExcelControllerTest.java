package com.qmdx.ams.controller;

import com.qmdx.ams.QmdxAmsApplicationTests;
import com.qmdx.ams.bo.FileUploadBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.excel.ExcelOperation;
import com.qmdx.ams.excel.ExcelStudentAdvisor;
import com.qmdx.ams.service.ExcelService;
import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;

import java.io.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shiex-薛
 * @title: ExcelControllerTest
 * @projectName qmdx-ams
 * @description: TODO
 * @date 2019\10\20 002018:59
 */
public class ExcelControllerTest extends QmdxAmsApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ExcelControllerTest.class);

    @Resource
    private ExcelService excelService;

    private final String suffixType = "xls,xlsx";

    @Test
    public void fileUpload() throws Exception {
        // input
        String excelType = "student"; // student或者company
        File file = new File("E:\\studentInfo.xlsx"); // 对应上传的表格类型
        JSONObject input = new JSONObject();
        input.put("excelType", excelType);
        input.put("fileName", file.getName());
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        InputStream inputStream = new FileInputStream(file);
        if(inputStream != null) {
            FileUploadBO fileUploadBO = new FileUploadBO(file.getName());

            if(!suffixType.contains(fileUploadBO.getSuffix())){
                out.put("error", "upload only xls/xlsx file");
                logger.info("out：" + out.toString());
                return;
            }

            try{
                List excelDataList = ExcelOperation.read(excelType, inputStream);
                if (excelDataList.size() > 0) {
                    if (excelDataList.get(0).getClass() == ExcelStudentAdvisor.class) {
                        out.put("excelDataList", excelDataList);
                    } else {
                        out.put("excelDataList", excelDataList);
                    }
                }
            }catch (IOException e){
                out = new JSONObject();
                out.put("error", e.getMessage());
            }
            logger.info("out：" + out.toString());
        }
    }

    @Test
    public void deriveExcel() throws Exception {
        // input
        String type = "studentInfo"; // studentInfo或companyInfo
        JSONObject input = new JSONObject();
        input.put("type", type);
        logger.info("input：" + input.toString());

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            // 下载表格
            String excelName = null;
            if (StringUtils.equals(type, "studentInfo")) excelName = "studentInfo.xlsx";
            if (StringUtils.equals(type, "companyInfo")) excelName = "companyInfo.xlsx";
            String classPath = ResourceUtils.getURL("classpath:").getPath().substring(1);
            File excelFile = new File(classPath, "/static/asserts/excel/" + excelName);

            inputStream = new FileInputStream(excelFile);
            outputStream = new FileOutputStream("E:\\excel\\" + excelName);
            streamCopy(inputStream, outputStream);

            JSONObject out = new JSONObject();
            out.put("deriveFilePath：",  "E:\\excel\\" + excelName);
            logger.info("out：" + out.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
                outputStream.close();
            }catch (Exception e){

            }
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