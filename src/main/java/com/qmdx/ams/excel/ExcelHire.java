package com.qmdx.ams.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcelHire extends BaseRowModel {

    @ExcelProperty(value = "studentId", index = 0)
    private Integer studentId;
    @ExcelProperty(value = "studentName", index = 1)
    private String studentName;
    @ExcelProperty(value = "companyName", index = 2)
    private String companyName;
    @ExcelProperty(value = "positionName", index = 3)
    private String positionName;
    @ExcelProperty(value = "academicYear", index = 4)
    private String academicYear;
    @ExcelProperty(value = "cooperationPlan", index = 5)
    private String cooperationPlan;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getCooperationPlan() {
        return cooperationPlan;
    }

    public void setCooperationPlan(String cooperationPlan) {
        this.cooperationPlan = cooperationPlan;
    }

    @Override
    public String toString() {
        return "ExcelDerive{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", cooperationPlan='" + cooperationPlan + '\'' +
                '}';
    }
}
