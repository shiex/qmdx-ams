package com.qmdx.ams.bo;

import com.qmdx.ams.utils.FileUploadUtil;

public class FileUploadBO {

    private String originalFilename;
    private String suffix;
    private String tmpFileName;

    private FileUploadBO(){}

    public FileUploadBO(String originalFilename){
        this.originalFilename = originalFilename;
        this.suffix = FileUploadUtil.fileSuffix(this.originalFilename);
        this.tmpFileName = FileUploadUtil.createTmpFileName(this.suffix);
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getTmpFileName() {
        return tmpFileName;
    }

}
