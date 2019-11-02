package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

public class Advisor extends Model<Advisor> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "advisor_id", type = IdType.NONE)
    private Integer advisorId;

    private String advisorName;

    private String advisorEmail;

    private Boolean advisorType;

    public Integer getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId) {
        this.advisorId = advisorId;
    }
    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }
    public String getAdvisorEmail() {
        return advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }
    public Boolean getAdvisorType() {
        return advisorType;
    }

    public void setAdvisorType(Boolean advisorType) {
        this.advisorType = advisorType;
    }



    @Override
    protected Serializable pkVal() {
        return this.advisorId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(!(obj instanceof Advisor)){
            return false;
        }
        Advisor advisor = (Advisor) obj;
        return this.toString().equals(advisor.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (advisorId == null?0:advisorId.hashCode());
        result = 31 * result + (advisorName == null?0:advisorName.hashCode());
        result = 31 * result + (advisorEmail == null?0:advisorEmail.hashCode());
        return result;
        /*return Objects.hash(id,name,state,intro);*/
    }

    @Override
    public String toString() {
        return "Advisor{" +
            "advisorId=" + advisorId +
            ", advisorName=" + advisorName +
            ", advisorEmail=" + advisorEmail +
            ", advisorType=" + advisorType +
        "}";
    }
}
