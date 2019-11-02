package com.qmdx.ams.service;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Company;
import com.qmdx.ams.entity.CompanyApplication;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyApplicationService extends IService<CompanyApplication> {

    /**
     * Query the company application list paging data.Query parameters
     * are encapsulated in PageInfoBO - company, occupation, student information
     * @param pageInfoBO
     * @return: java.util.List<com.qmdx.ams.entity.Company>
     */
    List<CompanyApplication> selectPageHelperList(PageInfoBO pageInfoBO);

    /**
     *
     * @param companyApplicationId
     * @return: com.qmdx.ams.entity.CompanyApplication
     */
    CompanyApplication selectOneJoin(Integer companyApplicationId);

    /**
     * Check application status
     * @param companyApplicationId
     * @return: int
     */
    int selectApplicationStatus(Integer companyApplicationId);

    /**
     * Check student email
     * @param companyApplicationId
     * @return: java.lang.String
     */
    String selectStudentEmail(Integer companyApplicationId);

    /**
     * Check whether the interview time exists
     * @param companyApplication
     * @return: boolean
     */
    boolean selectIsExistInterviewTime(CompanyApplication companyApplication);

    void updateStatusById(CompanyApplication companyApplication);

    void updateInterviewTimeById(CompanyApplication companyApplication);
}
