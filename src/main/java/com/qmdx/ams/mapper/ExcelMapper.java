package com.qmdx.ams.mapper;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.excel.ExcelCompanyPosition;
import com.qmdx.ams.excel.ExcelStudentAdvisor;

import java.util.List;

public interface ExcelMapper {

    /**
     * Paging the student table data
     * @param pageInfoBO
     * @return: java.util.List<com.qmdx.ams.excel.ExcelStudentAdvisor>
     */
    List<ExcelStudentAdvisor> selectPageHelperStudentList(PageInfoBO pageInfoBO);

    /**
     * Paging the company table data
     * @param pageInfoBO
     * @return: java.util.List<com.qmdx.ams.excel.ExcelCompanyPosition>
     */
    List<ExcelCompanyPosition> selectPageHelperCompanyList(PageInfoBO pageInfoBO);

    /**
     * Paging the number of student tables
     * @param
     * @return: int
     */
    int selectCountStudentAdvisor();

    /**
     * Paging the number of company tables
     * @param
     * @return: int
     */
    int selectCountCompanyPosition();
}
