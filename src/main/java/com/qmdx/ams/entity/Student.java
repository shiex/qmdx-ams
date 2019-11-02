package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id", type = IdType.NONE)
    private Integer studentId;

    private String studentName;

    private String studentEmail;

    private Short applicationCount;

    private List<StudentTime> studentTimeList;

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

    public Short getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(Short applicationCount) {
        this.applicationCount = applicationCount;
    }

    public List<StudentTime> getStudentTimeList() {
        return studentTimeList;
    }

    public void setStudentTimeList(List<StudentTime> studentTimeList) {
        this.studentTimeList = studentTimeList;
    }

    @Override
    protected Serializable pkVal() {
        return this.studentId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(!(obj instanceof Student)){
            return false;
        }
        Student student = (Student) obj;
        return this.toString().equals(student.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (studentId == null?0:studentId.hashCode());
        result = 31 * result + (studentName == null?0:studentName.hashCode());
        result = 31 * result + (studentEmail == null?0:studentEmail.hashCode());
        return result;
        /*return Objects.hash(id,name,state,intro);*/
    }

    @Override
    public String toString() {
        return "Student{" +
            "studentId=" + studentId +
            ", studentName=" + studentName +
            ", studentEmail=" + studentEmail +
        "}";
    }
}
