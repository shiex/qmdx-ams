package com.qmdx.ams.service.impl;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.excel.ExcelCompanyPosition;
import com.qmdx.ams.excel.ExcelParsingData;
import com.qmdx.ams.excel.ExcelStudentAdvisor;
import com.qmdx.ams.mapper.ExcelMapper;
import com.qmdx.ams.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource private ExcelMapper excelMapper;
    @Resource private UserService userService;
    @Resource private UserRoleService userRoleService;
    @Resource private StudentService studentService;
    @Resource private AdvisorService advisorService;
    @Resource private StudentGeneralAdvisorService studentGeneralAdvisorService;
    @Resource private StudentProjectAdvisorService studentProjectAdvisorService;
    @Resource private CompanyService companyService;
    @Resource private PositionService positionService;
    @Resource private CompanyPositionService companyPositionService;

    @Override
    public void insertStudentAdvisor(List excelDataList) {
        Set<User> userSet = new HashSet<>();
        Set<UserRole> userRoleSet = new HashSet<>();
        Set<Student> studentSet = new HashSet<>();
        Set<Advisor> advisorSet = new HashSet<>();
        Set<StudentGeneralAdvisor> studentGeneralAdvisorSet = new HashSet<>();
        Set<StudentProjectAdvisor> studentProjectAdvisorSet = new HashSet<>();
        for (Object data : excelDataList) {
            ExcelParsingData excelParsingData = new ExcelParsingData((ExcelStudentAdvisor) data);
            userSet.add(excelParsingData.getStudentUser());
            userRoleSet.add(excelParsingData.getStudentUserRole());
            studentSet.add(excelParsingData.getStudent());
            advisorSet.add(excelParsingData.getGeneralAdvisor());
            advisorSet.add(excelParsingData.getProjectAdvisor());
            studentGeneralAdvisorSet.add(excelParsingData.getStudentGeneralAdvisor());
            studentProjectAdvisorSet.add(excelParsingData.getStudentProjectAdvisor());
        }
        userService.saveBatch(userSet);
        userRoleService.saveBatch(userRoleSet);
        studentService.saveBatch(studentSet);
        advisorService.saveBatch(advisorSet);
        studentGeneralAdvisorService.saveBatch(studentGeneralAdvisorSet);
        studentProjectAdvisorService.saveBatch(studentProjectAdvisorSet);
    }

    @Override
    public void insertCompanyPosition(List excelDataList) {
        Set<Company> companySet = new HashSet<>();
        Set<User> userSet = new HashSet<>();
        Set<UserRole> userRoleSet = new HashSet<>();
        List<CompanyPosition> companyPositionList = new ArrayList<>();
        for (Object data : excelDataList) {
            ExcelParsingData excelParsingData = new ExcelParsingData((ExcelCompanyPosition) data);
            companySet.add(excelParsingData.getCompany());
            userSet.add(excelParsingData.getCompanyUser());
            userRoleSet.add(excelParsingData.getCompanyUserRole());
            positionService.save(excelParsingData.getPosition());
            companyPositionList.add(excelParsingData.getCompanyPosition());
        }
        companyService.saveBatch(companySet);
        userService.saveBatch(userSet);
        userRoleService.saveBatch(userRoleSet);
        companyPositionService.saveBatch(companyPositionList);
    }

    @Override
    public List<ExcelStudentAdvisor> selectPageHelperStudentList(PageInfoBO pageInfoBO) {
        return excelMapper.selectPageHelperStudentList(pageInfoBO);
    }

    @Override
    public List<ExcelCompanyPosition> selectPageHelperCompanyList(PageInfoBO pageInfoBO) {
        return excelMapper.selectPageHelperCompanyList(pageInfoBO);
    }

    @Override
    public int selectCountStudentAdvisor() {
        return excelMapper.selectCountStudentAdvisor();
    }

    @Override
    public int selectCountCompanyPosition() {
        return excelMapper.selectCountCompanyPosition();
    }
}
