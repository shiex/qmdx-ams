package com.qmdx.ams.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "company_id", type = IdType.NONE)
    private Integer companyId;

    private String companyName;

    private String typeOfBusiness;

    private String tel;

    private String location;

    private String email;

    private List<Position> positionList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    @Override
    protected Serializable pkVal() {
        return this.companyId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(!(obj instanceof Company)){
            return false;
        }
        Company company = (Company) obj;
        return this.toString().equals(company.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (companyId == null?0:companyId.hashCode());
        result = 31 * result + (companyName == null?0:companyName.hashCode());
        result = 31 * result + (email == null?0:email.hashCode());
        result = 31 * result + (tel == null?0:tel.hashCode());
        result = 31 * result + (location == null?0:location.hashCode());
        result = 31 * result + (typeOfBusiness == null?0:typeOfBusiness.hashCode());
        return result;
        /*return Objects.hash(id,name,state,intro);*/
    }

    @Override
    public String toString() {
        return "Company{" +
            "companyId=" + companyId +
            ", companyName=" + companyName +
            ", companyType=" + typeOfBusiness +
            ", tel=" + tel +
            ", location=" + location +
            ", email=" + email +
        "}";
    }
}
