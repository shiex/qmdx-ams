package com.qmdx.ams.controller;

import com.qmdx.ams.QmdxAmsApplicationTests;
import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.entity.Company;
import com.qmdx.ams.entity.CompanyApplication;
import com.qmdx.ams.entity.FreeTime;
import com.qmdx.ams.entity.Student;
import com.qmdx.ams.excel.ExcelCompanyPosition;
import com.qmdx.ams.excel.ExcelParsingData;
import com.qmdx.ams.excel.ExcelStudentAdvisor;
import com.qmdx.ams.service.*;
import com.qmdx.ams.utils.DateUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shiex-薛
 * @title: StaffControllerTest
 * @projectName qmdx-ams
 * @description: TODO
 * @date 2019\10\20 002018:59
 */
public class StaffControllerTest extends QmdxAmsApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(StaffControllerTest.class);

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

    @Test
    public void companyLoadData() throws Exception {
        // input
        PageInfoBO pageInfoBO = null;
        int page = 1;
        int limit = 15;
        Integer status = null; // 查询状态
        String queryType = null; // 查询类型
        String queryCondition = null; // 查询条件
        if (status == null) {
            pageInfoBO = new PageInfoBO(queryType, queryCondition, page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, queryType, queryCondition, page, limit);
        }
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        int total = companyApplicationService.count();
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("data", companyApplicationList);
        out.put("count", total);
        logger.info("out：" + out.toString());
    }

    @Test
    public void staffStudentTable() throws Exception {
        // input
        int page = 1;
        int limit = 15;
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<ExcelStudentAdvisor> studentAdvisorList = excelService.selectPageHelperStudentList(pageInfoBO);
        int total = excelService.selectCountStudentAdvisor();
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("data", studentAdvisorList);
        out.put("count", total);
        logger.info("out：" + out.toString());
    }

    @Test
    public void studentTableSave() throws Exception {
        ExcelStudentAdvisor excelStudentAdvisor = new ExcelStudentAdvisor();
        excelStudentAdvisor.setStudentId(null); // id不可编辑，根据id修改数据
        excelStudentAdvisor.setStudentName(null);
        excelStudentAdvisor.setStudentEmail(null);
        excelStudentAdvisor.setProjectAdvisorId(null); // id不可编辑，根据id修改数据
        excelStudentAdvisor.setProjectAdvisorName(null);
        excelStudentAdvisor.setProjectAdvisorEmail(null);
        excelStudentAdvisor.setGeneralAdvisorId(null); // id不可编辑，根据id修改数据
        excelStudentAdvisor.setGeneralAdvisorName(null);
        excelStudentAdvisor.setGeneralAdvisorEmail(null);
        JSONObject input = new JSONObject();
        input.put("excelStudentAdvisor", excelStudentAdvisor);
        logger.info("input：" + input.toString());

        // out
        ExcelParsingData excelParsingData = new ExcelParsingData(excelStudentAdvisor);
        JSONObject out = new JSONObject();
        out.put("student", excelParsingData.getStudent());
        out.put("generalAdvisor", excelParsingData.getGeneralAdvisor());
        out.put("projectAdvisor", excelParsingData.getProjectAdvisor());
        logger.info("out：" + out.toString());
    }

    @Test
    public void staffCompanyTable() throws Exception {
        // input
        int page = 1;
        int limit = 15;
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<ExcelCompanyPosition> companyPositionList = excelService.selectPageHelperCompanyList(pageInfoBO);
        int total = excelService.selectCountCompanyPosition();
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("data", companyPositionList);
        out.put("count", total);
        logger.info("out：" + out.toString());
    }

    @Test
    public void companyTableSave() throws Exception {
        // input
        ExcelCompanyPosition excelCompanyPosition = new ExcelCompanyPosition();
        excelCompanyPosition.setCompanyId(null); // id不可编辑，根据id修改数据
        excelCompanyPosition.setCompanyName(null);
        excelCompanyPosition.setEmail(null);
        excelCompanyPosition.setLocation(null);
        excelCompanyPosition.setTel(null);
        excelCompanyPosition.setTypeOfBusiness(null);
        excelCompanyPosition.setPositionId(null); // id不可编辑，根据id修改数据
        excelCompanyPosition.setPositionName(null);
        excelCompanyPosition.setNumOfPosition(null);
        JSONObject input = new JSONObject();
        input.put("excelCompanyPosition", excelCompanyPosition);
        logger.info("input：" + input.toString());

        // out
        ExcelParsingData excelParsingData = new ExcelParsingData(excelCompanyPosition);
        JSONObject out = new JSONObject();
        out.put("company", excelParsingData.getCompany());
        out.put("position", excelParsingData.getPosition());
        logger.info("out：" + out.toString());
    }

    @Test
    public void applicationStatus() throws Exception {
        // input
        Integer companyApplicationId = 1;
        int applicationStatus = companyApplicationService.selectApplicationStatus(companyApplicationId);
        JSONObject input = new JSONObject();
        input.put("companyApplicationId", companyApplicationId);
        logger.info("input：" + input.toString());

        JSONObject out = new JSONObject();
        String text = "No idle or interview time can be set";
        if (applicationStatus == 2) {
            out.put("error", "This application is currently in interview status." + text);
        } else if (applicationStatus == 3) {
            out.put("error", "The application is currently in employment status." + text);
        } else if (applicationStatus == 4) {
            out.put("error", "This application is currently in the status of administrator operation." + text);
        } else if (applicationStatus == 5) {
            out.put("error", "This application is currently in reject status." + text);
        } else {
            out.put("ok", "ok");
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void addFreeTime() throws Exception {
        FreeTime freeTime = new FreeTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        freeTime.setStartDate(sdf.parse("2019-10-19"));
        freeTime.setStartTime(new Time(System.currentTimeMillis()));
        freeTime.setEndDate(sdf.parse("2019-10-20"));
        freeTime.setEndTime(new Time(System.currentTimeMillis()));
        // input
        JSONObject input = new JSONObject();
        input.put("freeTime", freeTime);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        if (freeTimeService.count() > 0) {
            out.put("error", "Idle time already exists and cannot be added more than once");
        } else {
            freeTimeService.save(freeTime);
            out.put("freeTime", freeTime);
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void freeTimeListData() throws Exception {
        // input
        int page = 1;
        int limit = 15;
        PageInfoBO pageInfoBO = new PageInfoBO(page, limit);
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<Student> studentList = studentService.selectAllJoinStudentTime(pageInfoBO);
        int count = studentService.count();
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("count", count);
        out.put("data", studentList);
        logger.info("out：" + out.toString());
    }

    @Test
    public void staffInterviewTimePage() throws Exception {
        // input
        Integer applicationId = null;
        JSONObject input = new JSONObject();
        input.put("applicationId", applicationId);
        logger.info("input：" + input.toString());

        // out
        CompanyApplication companyApplication = companyApplicationService.selectOneJoin(applicationId);
        JSONObject out = new JSONObject();
        out.put("companyApplication", companyApplication);
        logger.info("out：" + out.toString());
    }

    @Test
    public void interviewTime() throws Exception {
        // input
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.getCompany().setCompanyName("buzo@gmail.com");
        companyApplication.getPosition().setPositionName("Full Stack Developer");
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setCompanyId(301);
        companyApplication.setInterviewTime("2019-01-22 09:10:11");
        JSONObject input = new JSONObject();
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            out.put("error", "The interview time already exists. Please reset it");
        } else {
            CompanyApplication application = companyApplicationService.selectOneJoin(companyApplication.getCompanyApplicationId());
            if (application.getInterviewTime() == null) {
                companyApplication.setApplicationStatus(4);
                companyApplicationService.updateById(companyApplication);
                out.put("companyApplication", companyApplication);

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
                out = new JSONObject();
                out.put("error", "The interview time for this application already exists and cannot be repeated");
            }
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void appointmentTimeListData() throws Exception{
        // input
        JSONObject input = new JSONObject();
        int studentId = 592115513;
        input.put("studentId", studentId);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        out.put("data", studentTimeService.selectAllByStudentId(studentId));
        logger.info("out：" + out.toString());
    }

    @Test
    public void addAppointmentTime() throws Exception{
        // input
        JSONObject input = new JSONObject();
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setInterviewTime("2019-10-09 09:10:11");
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setCompanyId(301);
        companyApplication.setApplicationStatus(6);
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            out.put("error", "The interview time already exists. Please reset it");
        } else {
            companyApplicationService.updateById(companyApplication);
            out.put("companyApplication", companyApplication);
        }
        logger.info("out：" + out.toString());
    }
}