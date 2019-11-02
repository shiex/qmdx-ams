package com.qmdx.ams.controller;

import com.qmdx.ams.QmdxAmsApplicationTests;
import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.service.*;
import com.qmdx.ams.utils.DateUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shiex-薛
 * @title: StudentControllerTest
 * @projectName qmdx-ams
 * @description: TODO
 * @date 2019\10\20 002018:59
 */
public class StudentControllerTest extends QmdxAmsApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(StaffControllerTest.class);

    @Resource
    private CompanyApplicationService companyApplicationService;
    @Resource
    private CompanyService companyService;
    @Resource
    private PositionService positionService;
    @Resource
    private UserService userService;
    @Resource
    private EmailService emailService;
    @Resource
    private StudentService studentService;
    @Resource
    private StudentTimeService studentTimeService;
    @Resource
    private FreeTimeService freeTimeService;

    @Test
    public void studentApplyFor() throws Exception {
        // input
        JSONObject input = new JSONObject();
        User user = new User();
        user.setUserId(592115513);
        input.put("user", user);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        out.put("data", studentTimeService.selectCountByStudentId(user.getUserId()));
        logger.info("out：" + out.toString());
    }

    @Test
    public void studentApplyForAdd() throws Exception {
        CompanyApplication companyApplication = new CompanyApplication();
        Company c = new Company();
        c.setCompanyName("xx公司");
        Position p = new Position();
        p.setPositionName("xx职位");
        companyApplication.setCompany(c);
        companyApplication.setPosition(p);
        companyApplication.setCompanyId(301);
        companyApplication.setPositionId(1);
        companyApplication.setAcademicYear("2017-2019");
        companyApplication.setCooperationPlan("12个月");
        User user = new User();
        user.setUserId(592115513);
        // input
        JSONObject input = new JSONObject();
        input.put("companyApplication", companyApplication);
        input.put("user", user);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        Position position = positionService.getById(companyApplication.getPositionId());
        if (position.getNumOfPosition() == position.getApplicationCount()) {
            out.put("error", "The number of applications for this position has reached the maximum!");
            logger.info("out：" + out.toString());
            return;
        }

        int ApplicationCount = studentService.selectApplicationCountById(user.getUserId());
        if (ApplicationCount >= 5) {
            out.put("error", "Limit of application, Only 5 companies can apply!");
            logger.info("out：" + out.toString());
            return;
        }
        String companyName = companyApplication.getCompany().getCompanyName();
        String positionName = companyApplication.getPosition().getPositionName();
        companyApplication.setCompany(null);
        companyApplication.setPosition(null);
        companyApplication.setStudentId(user.getUserId());
        companyApplicationService.save(companyApplication);
        studentService.addApplicationCount(user.getUserId());
        positionService.addApplicationCount(companyApplication.getPositionId());
        out.put("companyApplication", companyApplication);
        logger.info("out：" + out.toString());

        try {
            // Send emails to employees and companies
            String emailTitle = "Apply for notification";
            String content = String.format(
                    "student --- [%s] applied for %s company %s position",
                    user.getUserName(),
                    companyName,
                    positionName
            );
            List<String> staffEmailList = userService.selectStaffEmailAll();
            for (String staffEmail : staffEmailList) {
                emailService.sendSimpleMail(staffEmail, emailTitle, content);
            }
            String companyEmail = companyService.selectCompanyEmaliOne(companyApplication.getCompanyId());
            emailService.sendSimpleMail(companyEmail, emailTitle, content);
        } catch (Exception e) {
            out = new JSONObject();
            out.put("out", e.getMessage());
            logger.info("out：" + out.toString());
            e.printStackTrace();
        }
    }

    @Test
    public void applicationData() throws Exception {
        // input
        User user = new User();
        PageInfoBO pageInfoBO = null;
        Integer status = null;
        int page = 1;
        int limit = 15;
        if (status == null) {
            pageInfoBO = new PageInfoBO("student", user.getUserId().toString(), page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, "student", user.getUserId().toString(), page, limit);
        }
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("data", companyApplicationList);
        out.put("count", 0);
        logger.info("out：" + out.toString());
    }

    @Test
    public void studentFreeTime() throws Exception {
        // out
        JSONObject out = new JSONObject();
        List<FreeTime> freeTimeList = freeTimeService.list();
        if (freeTimeList.size() > 0) {
            FreeTime freeTime = freeTimeList.get(freeTimeList.size() - 1);
            out.put("freeTime", freeTime);
            out.put("startDate", DateUtil.parseDate(freeTime.getStartDate(), "yyyy-MM-dd"));
            out.put("endDate", DateUtil.parseDate(freeTime.getEndDate(), "yyyy-MM-dd"));
            out.put("startTime", freeTime.getStartTime().toString());
            out.put("endTime", freeTime.getEndTime().toString());
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void sendMessage() throws Exception {
        String emailTitle = "Idle time notification";
        String content = "Students request, please set free time as soon as possible to provide students with a choice";
        List<String> staffEmailList = userService.selectStaffEmailAll();
        try {
            for (String staffEmail : staffEmailList) {
                emailService.sendSimpleMail(staffEmail, emailTitle, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // out
        JSONObject out = new JSONObject();
        out.put("staffEmailList", staffEmailList);
        logger.info("out：" + out.toString());
    }

    @Test
    public void addStudentFreeTime() throws Exception {
        // input
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StudentTime studentTime = new StudentTime();
        studentTime.setFreeTime(sdf.parse("2019-08-11 09:11:12"));
        User user = new User();
        studentTime.setStudentId(user.getUserId());
        JSONObject input = new JSONObject();
        input.put("studentTime", studentTime);
        input.put("user", user);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        if (studentTimeService.selectCountByStudentIdAndTime(studentTime) > 0) {
            out.put("error", "This idle time already exists and cannot be selected repeatedly");
        } else {
            studentTimeService.save(studentTime);
            out.put("studentTime", studentTime);
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void interviewAccept() throws Exception {
        // input
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setApplicationStatus(2);
        companyApplicationService.updateById(companyApplication);
        JSONObject input = new JSONObject();
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        try {
            CompanyApplication application = companyApplicationService.selectOneJoin(companyApplication.getCompanyApplicationId());
            // Send emails to employees and companies
            String emailTitle = "interview notice";
            String content = String.format(
                    "%s student accepted the interview invitation of %s company",
                    application.getStudent().getStudentName(),
                    application.getCompany().getCompanyName()
            );
            List<String> staffEmailList = userService.selectStaffEmailAll();
            for (String staffEmail : staffEmailList) {
                emailService.sendSimpleMail(staffEmail, emailTitle, content);
            }
            emailService.sendSimpleMail(application.getCompany().getEmail(), emailTitle, content);
            out.put("message", "Send emails to employees and companies");
        } catch (Exception e) {
            out = new JSONObject();
            out.put("error", e.getMessage());
            logger.info("out:：" + out.toString());
            e.printStackTrace();
        }
        logger.info("out:：" + out.toString());
    }

    @Test
    public void interviewRefuse() throws Exception {
        // input
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setApplicationStatus(5);
        companyApplicationService.updateById(companyApplication);
        JSONObject input = new JSONObject();
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        companyApplicationService.updateById(companyApplication);
        try {
            CompanyApplication application = companyApplicationService.selectOneJoin(companyApplication.getCompanyApplicationId());
            // Send emails to employees and companies
            String emailTitle = "interview notice";
            String content = String.format(
                    "%s student rejected  the interview invitation of %s company, Transfer to employee",
                    application.getStudent().getStudentName(),
                    application.getCompany().getCompanyName()
            );
            List<String> staffEmailList = userService.selectStaffEmailAll();
            for (String staffEmail : staffEmailList) {
                emailService.sendSimpleMail(staffEmail, emailTitle, content);
            }
            emailService.sendSimpleMail(application.getCompany().getEmail(), emailTitle, content);
            out.put("message", "Send emails to employees and companies");
        } catch (Exception e) {
            out = new JSONObject();
            out.put("error", e.getMessage());
            logger.info("out:：" + out.toString());
            e.printStackTrace();
        }
    }
}