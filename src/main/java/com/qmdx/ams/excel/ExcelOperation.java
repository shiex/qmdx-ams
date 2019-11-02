package com.qmdx.ams.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

public class ExcelOperation {

    public static List<Object> read(String fileType, InputStream inputStream) throws IOException {
        int k = 1;
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
        if (fileType.equals("student")) {
            excelReader.read(new Sheet(k, k, ExcelStudentAdvisor.class));
        }
        if (fileType.equals("company")) {
            excelReader.read(new Sheet(k, k, ExcelCompanyPosition.class));
        }
        return listener.getDatas();
    }

}
