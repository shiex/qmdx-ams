package com.qmdx.ams.controller;


import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.service.*;
import com.qmdx.ams.utils.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth/student")
public class StudentController {

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

    @ResponseBody
    @RequestMapping("/applyfor")
    public Map<String, Object> studentApplyFor(HttpSession session) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        restMap.put("data", studentTimeService.selectCountByStudentId(user.getUserId()));
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/applyfor/add")
    public Map<String, Object> studentApplyForAdd(@RequestBody CompanyApplication companyApplication,
                                                  HttpSession session) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        /*
         *  Check whether there is idle time, if there is
         *  check whether the position is full, if not
         *  jump to the page of setting idle time
         */
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        Position position = positionService.getById(companyApplication.getPositionId());
        if (position.getNumOfPosition() == position.getApplicationCount()) {
            RestMapBO.setErrorRestMap(restMap, "The number of applications for this position has reached the maximum!");
            return restMap;
        }

        int ApplicationCount = studentService.selectApplicationCountById(user.getUserId());
        if (ApplicationCount >= 5) {
            RestMapBO.setErrorRestMap(restMap, "Limit of application, Only 5 companies can apply!");
            return restMap;
        }
        String companyName = companyApplication.getCompany().getCompanyName();
        String positionName = companyApplication.getPosition().getPositionName();
        companyApplication.setCompany(null);
        companyApplication.setPosition(null);
        companyApplication.setStudentId(user.getUserId());
        companyApplicationService.save(companyApplication);
        studentService.addApplicationCount(user.getUserId());
        positionService.addApplicationCount(companyApplication.getPositionId());

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
            RestMapBO.setErrorRestMap(restMap, e.getMessage());
            e.printStackTrace();
        }

        return restMap;
    }

    @RequestMapping("/index")
    public String studentIndex() {
        return "/student/student";
    }

    @ResponseBody
    @RequestMapping("/application/data")
    public Map<String, Object> applicationData(@RequestParam("status") Integer status,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("limit") Integer limit,
                                               HttpSession session) {
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        PageInfoBO pageInfoBO = null;
        if (status == null) {
            pageInfoBO = new PageInfoBO("student", user.getUserId().toString(), page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, "student", user.getUserId().toString(), page, limit);
        }

        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("code", 0);
        restMap.put("data", companyApplicationList);
        return restMap;
    }

    @RequestMapping("/free/time")
    public String studentFreeTime(Model model) {
        List<FreeTime> freeTimeList = freeTimeService.list();
        if (freeTimeList.size() > 0) {
            FreeTime freeTime = freeTimeList.get(freeTimeList.size() - 1);
            model.addAttribute("freeTime", freeTime);
            model.addAttribute("startDate", DateUtil.parseDate(freeTime.getStartDate(), "yyyy-MM-dd"));
            model.addAttribute("endDate", DateUtil.parseDate(freeTime.getEndDate(), "yyyy-MM-dd"));
            model.addAttribute("startTime", freeTime.getStartTime().toString());
            model.addAttribute("endTime", freeTime.getEndTime().toString());
        }
        return "student/student_time";
    }

    @ResponseBody
    @RequestMapping("/send/message")
    public Map<String, Object> sendMessage() {
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
        return RestMapBO.getRestMap();
    }

    @ResponseBody
    @RequestMapping("/free/time/add")
    public Map<String, Object> addStudentFreeTime(@RequestBody StudentTime studentTime, HttpSession session) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        studentTime.setStudentId(user.getUserId());
        if (studentTimeService.selectCountByStudentIdAndTime(studentTime) > 0) {
            RestMapBO.setErrorRestMap(restMap, "This idle time already exists and cannot be selected repeatedly");
        } else {
            studentTimeService.save(studentTime);
        }
        return restMap;
    }

    @RequestMapping("/interview/list")
    public String studentInterviewList() {
        return "/student/interview_list";
    }

    @ResponseBody
    @RequestMapping("/interview/accept")
    public Map<String, Object> interviewAccept(@RequestBody CompanyApplication companyApplication) {
        companyApplication.setApplicationStatus(2);
        companyApplicationService.updateById(companyApplication);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestMapBO.getRestMap();
    }

    @ResponseBody
    @RequestMapping("/interview/refuse")
    public Map<String, Object> interviewRefuse(@RequestBody CompanyApplication companyApplication) {
        companyApplication.setApplicationStatus(5);
        companyApplicationService.updateStatusById(companyApplication);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestMapBO.getRestMap();
    }
}
