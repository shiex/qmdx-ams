package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

public class CompanyApplication extends Model<CompanyApplication> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "company_application_id", type = IdType.AUTO)
    private Integer companyApplicationId;

    private Integer companyId;

    private Integer positionId;

    private Integer studentId;

    private String academicYear;

    private String cooperationPlan;

    private String interviewTime;

    private String interviewLocation;

    private Integer applicationStatus;

    private Company company;
    private Position position;
    private Student student;
    private FreeTime freeTime;

    public Integer getCompanyApplicationId() {
        return companyApplicationId;
    }

    public void setCompanyApplicationId(Integer companyApplicationId) {
        this.companyApplicationId = companyApplicationId;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }
    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }
    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public FreeTime getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(FreeTime freeTime) {
        this.freeTime = freeTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.companyApplicationId;
    }

    @Override
    public String toString() {
        return "CompanyApplication{" +
            "companyApplicationId=" + companyApplicationId +
            ", companyId=" + companyId +
            ", positionId=" + positionId +
            ", studentId=" + studentId +
            ", academicYear=" + academicYear +
            ", cooperationPlan=" + cooperationPlan +
            ", interviewTime=" + interviewTime +
            ", interviewLocation=" + interviewLocation +
            ", applicationStatus=" + applicationStatus +
        "}";
    }
}
