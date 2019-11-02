package com.qmdx.ams.controller;

import com.qmdx.ams.QmdxAmsApplicationTests;
import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.service.*;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author shiex-薛
 * @title: CompanyControllerTest
 * @projectName qmdx-ams
 * @description: TODO
 * @date 2019\10\20 002018:58
 */
public class CompanyControllerTest extends QmdxAmsApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(CompanyControllerTest.class);

    @Resource
    private CompanyApplicationService companyApplicationService;
    @Resource
    private CompanyService companyService;
    @Resource
    private StudentTimeService studentTimeService;
    @Resource
    private StudentService studentService;
    @Resource
    private EmailService emailService;
    @Resource
    private UserService userService;

    // 访问首页，加载公司数据，Company对象作为查询参数，当ID为0时，加载所有公司前30条数据
    @Test
    public void index() throws Exception {
        // input
        logger.info("input：http://localhost:8080");

        // out
        Company company = new Company();
        company.setCompanyId(0);
        JSONObject input = new JSONObject();
        input.put("company", company);
        List<Company> companyList = companyService.selectDropDownList(company);
        int companyId = 0;
        if (companyList.size() > 0) {
            companyId = companyList.get(companyList.size()-1).getCompanyId();
        }
        JSONObject out = new JSONObject();
        out.put("companyList", companyList);
        out.put("companyId", companyId);
        logger.info("out：" + out.toString());
    }

    @Test
    public void find() throws Exception {
        // input
        JSONObject input = new JSONObject();
        String companyName = "C";
        Company company = new Company();
        company.setCompanyId(0);
        company.setCompanyName(companyName);
        input.put("company", company);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        List<Company> companyList = companyService.selectDropDownList(company);
        out.put("companyList", companyList);

        int companyId = 0;
        if (companyList.size() > 0) {
            companyId = companyList.get(companyList.size()-1).getCompanyId();
        }
        out.put("companyId", companyId);
        out.put("companyName", companyName);
        logger.info("out：" + out.toString());
    }

    @Test
    public void loadCompanyData() throws Exception {
        // input
        JSONObject input = new JSONObject();
        Company company = new Company();
        company.setCompanyId(301);
        company.setCompanyName(null);  // 不为null时附加公司名称查询
        input.put("company", company);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        List<Company> companyList = companyService.selectDropDownList(company);
        out.put("companyList", companyList);

        if (companyList.size() > 0) {
            out.put("companyId", companyList.get(companyList.size()-1).getCompanyId());
        }else {
            out.put("isLoad", 1);
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void companyPage() throws Exception {
        // input
        JSONObject input = new JSONObject();
        int companyId = 301;
        input.put("companyId", companyId);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        Company company = companyService.selectCompanyPosition(companyId);
        List<Position> positionList = company.getPositionList();
        out.put("company", company);
        out.put("positionList", positionList);
        logger.info("out：" + out.toString());
    }

    @Test
    public void companyIndex() {
        // 跳转页面
    }

    @Test
    public void companyInterviewList() {
        // 跳转页面
    }

    @Test
    public void companyLoadData() throws Exception {
        // input
        PageInfoBO pageInfoBO = null;
        User user = new User();
        user.setUserId(301);
        Integer status = null;  // status为2时，查询面试列表
        int page = 1;
        int limit = 15;
        if (status == null) {
            pageInfoBO = new PageInfoBO("company", user.getUserId().toString(), page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, "company", user.getUserId().toString(), page, limit);
        }
        JSONObject input = new JSONObject();
        input.put("pageInfoBO", pageInfoBO);
        logger.info("input：" + input.toString());

        // out
        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        JSONObject out = new JSONObject();
        out.put("code", 0);
        out.put("data", companyApplicationList);
        logger.info("out：" + out.toString());
    }

    @Test
    public void studentTimeList() throws Exception {
        // input
        int companyApplicationId = 1;
        JSONObject input = new JSONObject();
        input.put("companyApplicationId", companyApplicationId);
        logger.info("input：" + input.toString());

        // out
        List<StudentTime> studentTimeList = studentTimeService.selectAllByStudentId(companyApplicationId);
        JSONObject out = new JSONObject();
        out.put("data", studentTimeList);
        logger.info("out：" + out.toString());
    }

    @Test
    public void addInterview() throws Exception {
        // input
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setCompanyId(301);
        companyApplication.setStudentId(592115513);
        companyApplication.setInterviewTime("2019-01-18 08:11:20");
        JSONObject input = new JSONObject();
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        JSONObject out = new JSONObject();
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            out.put("error", "The interview time already exists. Please reset it");
            logger.info("out：" + out.toString());
            return;
        } else {
            companyApplication.setApplicationStatus(6);
            companyApplicationService.updateById(companyApplication);

            // Send emails to students and all staff
            try{
                User user = new User();
                user.setUserName("Siqi Fu");
                Student student = studentService.selectOneById(companyApplication.getStudentId());
                String emailTitle = "Notification of application";
                String content = String.format(
                        "%s company accepted the application of %s student",
                        user.getUserName(),
                        student.getStudentName()
                );

                // Send emails to employees and companies
                List<String> staffEmailList = userService.selectStaffEmailAll();
                for(String staffEmail : staffEmailList){
                    emailService.sendSimpleMail(staffEmail,emailTitle,content);
                }
                emailService.sendSimpleMail(student.getStudentEmail(),emailTitle,content);
            }catch (Exception e){
                out.put("error", e.getMessage());
                logger.info("out：" + out.toString());
                e.printStackTrace();
                return;
            }
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void addAppointment() throws Exception {
        // input
        JSONObject input = new JSONObject();
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setCompanyApplicationId(1);
        companyApplication.setStudentId(592115513);
        companyApplication.setApplicationStatus(2);
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());
        companyApplicationService.updateById(companyApplication);

        // out
        JSONObject out = new JSONObject();
        // Send emails to students and all staff
        try{
            User user = new User();
            user.setUserId(301);
            Student student = studentService.selectOneById(companyApplication.getStudentId());
            String emailTitle = "interview notice";
            String content = String.format(
                    "%s company has accepted the interview time set by employees for %s students. The interview mode is %s",
                    user.getUserName(),
                    student.getStudentName(),
                    companyApplication.getInterviewLocation()
            );

            // Send emails to employees and companies
            List<String> staffEmailList = userService.selectStaffEmailAll();
            for(String staffEmail : staffEmailList){
                emailService.sendSimpleMail(staffEmail,emailTitle,content);
            }
            emailService.sendSimpleMail(student.getStudentEmail(),emailTitle,content);
            out.put("companyApplication", companyApplication);
            out.put("message", "Send emails to employees and companies");
        }catch (Exception e){
            out = new JSONObject();
            out.put("error", e.getMessage());
            e.printStackTrace();
        }
        logger.info("out：" + out.toString());
    }

    @Test
    public void newInterviewTime() throws Exception {
        // input
        JSONObject input = new JSONObject();
        CompanyApplication companyApplication = new CompanyApplication();
        companyApplication.setCompanyApplicationId(301);
        companyApplication.setInterviewTime("2019-10-09 09:11:21");
        companyApplication.setInterviewLocation("面试地址或视频面试");
        input.put("companyApplication", companyApplication);
        logger.info("input：" + input.toString());

        // out
        JSONObject out = new JSONObject();
        User user = new User();
        user.setUserId(301);
        companyApplication.setCompanyId(user.getUserId());
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            out = new JSONObject();
            out.put("erorr", "The interview time already exists. Please reset it");
            logger.info("out：" + out.toString());
            return;
        } else {
            companyApplication.setApplicationStatus(1);
            companyApplicationService.updateInterviewTimeById(companyApplication);
            out.put("companyApplication", companyApplication);
            logger.info("out：" + out.toString());
        }
    }
}