package com.qmdx.ams.service;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface CompanyService extends IService<Company> {

    /**
     * Look up the company drop-down list -- the company and the occupation
     * @param company
     * @return: java.util.List<com.qmdx.ams.entity.Company>
     */
    List<Company> selectDropDownList(Company company);

    /**
     * Check the company and all positions
     * @param companyId
     * @return: com.qmdx.ams.entity.Company
     */
    Company selectCompanyPosition(Integer companyId);

    /**
     * Check company email
     * @param companyId
     * @return: java.lang.String
     */
    String selectCompanyEmaliOne(Integer companyId);
}
