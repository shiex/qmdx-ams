package com.qmdx.ams.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcelCompanyPosition extends BaseRowModel
{
	@ExcelProperty(index = 0)
	private Integer companyId ;

	@ExcelProperty(index = 1)
	private String companyName ;

	@ExcelProperty(index = 2)
	private String positionName;

	@ExcelProperty(index = 3)
	private Integer numOfPosition;

	@ExcelProperty(index = 4)
	private String tel ;

	@ExcelProperty(index = 5)
	private String typeOfBusiness ;

	@ExcelProperty(index = 6)
	private String location ;

	@ExcelProperty(index = 7)
	private String email ;

	private Integer positionId;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTypeOfBusiness() {
		return typeOfBusiness;
	}

	public void setTypeOfBusiness(String typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
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

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	@Override
	public String toString() {
		return "ExcelCompanyPosition{" +
				"companyId=" + companyId +
				", companyName='" + companyName + '\'' +
				", positionName='" + positionName + '\'' +
				", numOfPosition=" + numOfPosition +
				", tel='" + tel + '\'' +
				", typeOfBusiness='" + typeOfBusiness + '\'' +
				", location='" + location + '\'' +
				", email='" + email + '\'' +
				", positionId=" + positionId +
				'}';
	}
}
 