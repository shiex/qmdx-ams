package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

public class StudentProjectAdvisor extends Model<StudentProjectAdvisor> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer projectAdvisorId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProjectAdvisorId() {
        return projectAdvisorId;
    }

    public void setProjectAdvisorId(Integer projectAdvisorId) {
        this.projectAdvisorId = projectAdvisorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentProjectAdvisor{" +
                "studentId=" + studentId +
                ", projectAdvisorId=" + projectAdvisorId +
                '}';
    }
}
