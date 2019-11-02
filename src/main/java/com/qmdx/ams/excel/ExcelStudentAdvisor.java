package com.qmdx.ams.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcelStudentAdvisor extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Integer studentId ;

    @ExcelProperty(index = 1)
    private String studentName ;

    @ExcelProperty(index = 2)
    private String studentEmail ;

    private String studentPassword;

    @ExcelProperty(index = 3)
    private Integer projectAdvisorId;

    @ExcelProperty(index = 4)
    private String projectAdvisorName;

    @ExcelProperty(index = 5)
    private String projectAdvisorEmail;

    @ExcelProperty(index = 6)
    private Integer generalAdvisorId;

    @ExcelProperty(index = 7)
    private String generalAdvisorName;

    @ExcelProperty(index = 8)
    private String generalAdvisorEmail;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public Integer getProjectAdvisorId() {
        return projectAdvisorId;
    }

    public void setProjectAdvisorId(Integer projectAdvisorId) {
        this.projectAdvisorId = projectAdvisorId;
    }

    public String getProjectAdvisorName() {
        return projectAdvisorName;
    }

    public void setProjectAdvisorName(String projectAdvisorName) {
        this.projectAdvisorName = projectAdvisorName;
    }

    public String getProjectAdvisorEmail() {
        return projectAdvisorEmail;
    }

    public void setProjectAdvisorEmail(String projectAdvisorEmail) {
        this.projectAdvisorEmail = projectAdvisorEmail;
    }

    public Integer getGeneralAdvisorId() {
        return generalAdvisorId;
    }

    public void setGeneralAdvisorId(Integer generalAdvisorId) {
        this.generalAdvisorId = generalAdvisorId;
    }

    public String getGeneralAdvisorName() {
        return generalAdvisorName;
    }

    public void setGeneralAdvisorName(String generalAdvisorName) {
        this.generalAdvisorName = generalAdvisorName;
    }

    public String getGeneralAdvisorEmail() {
        return generalAdvisorEmail;
    }

    public void setGeneralAdvisorEmail(String generalAdvisorEmail) {
        this.generalAdvisorEmail = generalAdvisorEmail;
    }

    @Override
    public String toString() {
        return "ExcelStudentAdvisor{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", projectAdvisorId=" + projectAdvisorId +
                ", projectAdvisorName='" + projectAdvisorName + '\'' +
                ", projectAdvisorEmail='" + projectAdvisorEmail + '\'' +
                ", generalAdvisorId=" + generalAdvisorId +
                ", generalAdvisorName='" + generalAdvisorName + '\'' +
                ", generalAdvisorEmail='" + generalAdvisorEmail + '\'' +
                '}';
    }
}
