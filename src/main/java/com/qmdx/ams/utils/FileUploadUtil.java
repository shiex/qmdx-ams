package com.qmdx.ams.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtil {

    /**
     * @description: Generate a unique ID
     * @param
     * @return: java.lang.String
     */
    public static String createUUID () {
        String s = UUID.randomUUID().toString();
        String s2 = s.substring(24).replace("-","");
        return s2.toUpperCase();
    }

    /**
     * @description: Gets a temporary file name that is guaranteed not to repeat
     * @param suffix
     * @return: java.lang.String
     */
    public static String createTmpFileName(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String datestr = sdf.format(new Date());
        String name = datestr + "-" + createUUID() + "." + suffix;
        return name;
    }

    /**
     * @description: Gets the suffix name of the file
     * @param fileName
     * @return: java.lang.String
     */
    public static String fileSuffix(String fileName) {
        int p = fileName.lastIndexOf('.');
        if(p >= 0)
        {
            return fileName.substring(p+1).toLowerCase();
        }
        return "";
    }

}
