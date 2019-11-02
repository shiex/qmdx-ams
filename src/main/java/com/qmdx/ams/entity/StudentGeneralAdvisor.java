package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

public class StudentGeneralAdvisor extends Model<StudentGeneralAdvisor> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer generalAdvisorId;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGeneralAdvisorId() {
        return generalAdvisorId;
    }

    public void setGeneralAdvisorId(Integer generalAdvisorId) {
        this.generalAdvisorId = generalAdvisorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentGeneralAdvisor{" +
                "studentId=" + studentId +
                ", generalAdvisorId=" + generalAdvisorId +
                '}';
    }
}
