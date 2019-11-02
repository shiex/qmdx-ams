package com.qmdx.ams.mapper;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.CompanyApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyApplicationMapper extends BaseMapper<CompanyApplication> {
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

    int selectIsExistInterviewTime(CompanyApplication companyApplication);

    CompanyApplication selectOneById(Integer companyApplicationId);

    void updateStatusById(CompanyApplication companyApplication);

    void updateInterviewTimeById(CompanyApplication companyApplication);
}
