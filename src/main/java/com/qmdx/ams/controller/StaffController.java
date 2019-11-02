package com.qmdx.ams.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.excel.ExcelCompanyPosition;
import com.qmdx.ams.excel.ExcelParsingData;
import com.qmdx.ams.excel.ExcelStudentAdvisor;
import com.qmdx.ams.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth/staff")
public class StaffController {

    @Resource
    private CompanyApplicationService companyApplicationService;
    @Resource
    private StudentService studentService;
    @Resource
    private ExcelService excelService;
    @Resource
    private FreeTimeService freeTimeService;
    @Resource
    private EmailService emailService;
    @Resource
    private CompanyService companyService;
    @Resource
    private PositionService positionService;
    @Resource
    private AdvisorService advisorService;
    @Resource
    private StudentTimeService studentTimeService;

    @RequestMapping("/index")
    public String staffIndex() {
        return "/staff/staff";
    }

    @RequestMapping("/table/import")
    public String staffImportTable() {
        return "/staff/table";
    }

    @RequestMapping("/student/table/list")
    public String staffStudentTableList() {
        return "/staff/student_table";
    }

    @RequestMapping("/company/table/list")
    public String staffCompanyTableList() {
        return "/staff/company_table";
    }

    @RequestMapping("/free/time")
    public String freeTime() {
        return "/staff/free_time";
    }

    @RequestMapping("/free/time/list")
    public String freeTimeList() {
        return "/staff/free_time_list";
    }

    @RequestMapping("/interview/list")
    public String interviewListPage() {
        return "/staff/interview_list";
    }

    @ResponseBody
    @RequestMapping("/student/application/data")
    public Map<String, Object> companyLoadData (@RequestParam("status") Integer status,
                                                @RequestParam("queryType") String queryType,
                                                @RequestParam("queryCondition")String queryCondition,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("limit") Integer limit) {
        PageInfoBO pageInfoBO = null;
        if (status == null) {
            pageInfoBO = new PageInfoBO(queryType, queryCondition, page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, queryType, queryCondition, page, limit);
        }
        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        int total = companyApplicationService.count();
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("code", 0);
        restMap.put("data", companyApplicationList);
        restMap.put("count", total);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/student/table/data")
    public Map<String, Object> staffStudentTable(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit){
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        List<ExcelStudentAdvisor> studentAdvisorList = excelService.selectPageHelperStudentList(pageInfoBO);
        int total = excelService.selectCountStudentAdvisor();
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("code", 0);
        restMap.put("data", studentAdvisorList);
        restMap.put("count", total);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/student/table/save")
    public Map<String, Object> studentTableSave(@RequestBody ExcelStudentAdvisor excelStudentAdvisor){
        ExcelParsingData excelParsingData = new ExcelParsingData(excelStudentAdvisor);
        studentService.updateById(excelParsingData.getStudent());
        advisorService.updateById(excelParsingData.getGeneralAdvisor());
        advisorService.updateById(excelParsingData.getProjectAdvisor());
        return RestMapBO.getRestMap();
    }

    @ResponseBody
    @RequestMapping("/company/table/data")
    public Map<String, Object> staffCompanyTable(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit){
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        List<ExcelCompanyPosition> companyPositionList = excelService.selectPageHelperCompanyList(pageInfoBO);
        int total = excelService.selectCountCompanyPosition();
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("code", 0);
        restMap.put("data", companyPositionList);
        restMap.put("count", total);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/company/table/save")
    public Map<String, Object> companyTableSave(@RequestBody ExcelCompanyPosition excelCompanyPosition){
        ExcelParsingData excelParsingData = new ExcelParsingData(excelCompanyPosition);
        companyService.updateById(excelParsingData.getCompany());
        positionService.updateById(excelParsingData.getPosition());
        return RestMapBO.getRestMap();
    }

    @ResponseBody
    @RequestMapping("/application/status")
    public Map<String, Object> applicationStatus(@RequestBody Integer companyApplicationId){
        Map<String, Object> restMap = RestMapBO.getRestMap();
        int applicationStatus = companyApplicationService.selectApplicationStatus(companyApplicationId);
        restMap.put("data", applicationStatus);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/free/time/add")
    public Map<String, Object> addFreeTime(@RequestBody FreeTime freeTime) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        if (freeTimeService.count() > 0) {
            RestMapBO.setErrorRestMap(restMap, "Idle time already exists and cannot be added more than once");
        } else {
            freeTimeService.save(freeTime);
        }
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/free/time/list/data")
    public Map<String, Object> freeTimeListData(@RequestParam("page") int page,
                                                @RequestParam("limit") int limit){
        Map<String, Object> restMap = RestMapBO.getRestMap();
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        List<Student> studentList = studentService.selectAllJoinStudentTime(pageInfoBO);
        int count = studentService.count();
        restMap.put("code", 0);
        restMap.put("count", count);
        restMap.put("data", studentList);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/appointment/time/list")
    public Map<String, Object> appointmentTimeListData(@RequestBody int studentId){
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("data", studentTimeService.selectAllByStudentId(studentId));
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/appointment/time/add")
    public Map<String, Object> addAppointmentTime(@RequestBody CompanyApplication companyApplication){
        Map<String, Object> restMap = new HashMap<>();
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            RestMapBO.setErrorRestMap(restMap, "The interview time already exists. Please reset it");
        } else {
            companyApplication.setApplicationStatus(6);
            companyApplicationService.updateById(companyApplication);
        }
        return restMap;
    }

    @RequestMapping("/interview/time/{applicationId}")
    public String staffInterviewTimePage(@PathVariable("applicationId") Integer applicationId, Model model) {
        CompanyApplication companyApplication = companyApplicationService.selectOneJoin(applicationId);
        model.addAttribute("companyApplication", companyApplication);
        return "/staff/interview_time";
    }

    @ResponseBody
    @RequestMapping("/interview/time/add")
    public Map<String, Object> interviewTime(@RequestBody CompanyApplication companyApplication) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            RestMapBO.setErrorRestMap(restMap, "The interview time already exists. Please reset it");
        } else {
            CompanyApplication application = companyApplicationService.selectOneJoin(companyApplication.getCompanyApplicationId());
            if (application.getInterviewTime() == null) {
                companyApplication.setApplicationStatus(4);
                companyApplicationService.updateById(companyApplication);

                try {
                    String emailTitle = "interview notice";
                    String content = String.format(
                            "The interview time of %s student and %s company is defined by employees",
                            application.getStudent().getStudentName(),
                            application.getCompany().getCompanyName()
                    );
                    emailService.sendSimpleMail(application.getStudent().getStudentEmail(),emailTitle,content);
                    emailService.sendSimpleMail(application.getCompany().getEmail(),emailTitle,content);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                RestMapBO.setErrorRestMap(restMap, "The interview time for this application already exists and cannot be repeated");
            }
        }
        return restMap;
    }

}

