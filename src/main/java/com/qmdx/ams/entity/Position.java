package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "position_id", type = IdType.AUTO)
    private Integer positionId;

    private String positionName;

    private Integer numOfPosition;

    private Integer applicationCount;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public Integer getNumOfPosition() {
        return numOfPosition;
    }

    public void setNumOfPosition(Integer numOfPosition) {
        this.numOfPosition = numOfPosition;
    }
    public Integer getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(Integer applicationCount) {
        this.applicationCount = applicationCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.positionId;
    }

    @Override
    public String toString() {
        return "Position{" +
            "positionId=" + positionId +
            ", positionName=" + positionName +
            ", numOfPosition=" + numOfPosition +
            ", applicationCount=" + applicationCount +
        "}";
    }
}
