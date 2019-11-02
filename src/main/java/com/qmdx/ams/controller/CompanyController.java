package com.qmdx.ams.controller;


import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.*;
import com.qmdx.ams.service.*;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {

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

    @RequestMapping("/")
    public String index (Model model) {
        Company company = new Company();
        company.setCompanyId(0);
        List<Company> companyList = companyService.selectDropDownList(company);
        model.addAttribute("companyList", companyList);

        int companyId = 0;
        if (companyList.size() > 0) {
            companyId = companyList.get(companyList.size()-1).getCompanyId();
        }
        model.addAttribute("companyId", companyId);

        return "index";
    }

    /**
     * Query the company list by keyword fuzzy
     *
     * @param companyName
     * @param model
     * @return: java.lang.String
     */
    @RequestMapping("/company/find")
    public String find(@RequestParam("companyName") String companyName, Model model){
        Company company = new Company();
        company.setCompanyId(0);
        company.setCompanyName(companyName);
        List<Company> companyList = companyService.selectDropDownList(company);
        model.addAttribute("companyList", companyList);

        int companyId = 0;
        if (companyList.size() > 0) {
            companyId = companyList.get(companyList.size()-1).getCompanyId();
        }
        model.addAttribute("companyId", companyId);
        model.addAttribute("companyName", companyName);

        return "index";
    }

    @ResponseBody
    @RequestMapping("/company/load/data")
    public Map<String, Object> loadCompanyData(@RequestBody Company company) {
        List<Company> companyList = companyService.selectDropDownList(company);
        Map<String, Object> data = new HashMap<>();
        data.put("companyList", companyList);

        if (companyList.size() > 0) {
            data.put("companyId", companyList.get(companyList.size()-1).getCompanyId());
        }else {
            data.put("isLoad", 1);
        }

        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("data", data);

        return restMap;
    }

    /**
     * Company information page
     *
     * @param companyId
     * @return: java.lang.String
     */
    @RequestMapping("/company/{companyId}.html")
    public String companyPage(@PathVariable("companyId") Integer companyId, Model model){
        Company company = companyService.selectCompanyPosition(companyId);
        List<Position> positionList = company.getPositionList();
        model.addAttribute("company", company);
        model.addAttribute("positionList", positionList);
        return "/company/company_page";
    }

    @RequestMapping("/auth/company/index")
    public String companyIndex() {
        return "/company/company";
    }

    @RequestMapping("/auth/company/interview/list")
    public String companyInterviewList() {
        return "/company/interview_list";
    }

    @ResponseBody
    @RequestMapping("/auth/company/application/data")
    public Map<String, Object> companyLoadData (@RequestParam("status") Integer status,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("limit") Integer limit,
                                                HttpSession session) {
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        PageInfoBO pageInfoBO = null;
        if (status == null) {
            pageInfoBO = new PageInfoBO("company", user.getUserId().toString(), page, limit);
        }
        if (status != null) {
            pageInfoBO = new PageInfoBO(status, "company", user.getUserId().toString(), page, limit);
        }
        List<CompanyApplication> companyApplicationList = companyApplicationService.selectPageHelperList(pageInfoBO);
        Map<String, Object> restMap = RestMapBO.getRestMap();
        restMap.put("code", 0);
        restMap.put("data", companyApplicationList);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/auth/company/student/time/list")
    public Map<String, Object> studentTimeList(@RequestBody Integer companyApplicationId) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        List<StudentTime> studentTimeList = studentTimeService.selectAllByStudentId(companyApplicationId);
        restMap.put("data", studentTimeList);
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/auth/company/appointment/add")
    public Map<String, Object> addAppointment(@RequestBody CompanyApplication companyApplication, HttpSession session) {
        Map<String , Object> restMap = new HashMap<>();
        companyApplication.setApplicationStatus(2);
        companyApplicationService.updateById(companyApplication);
        // Send emails to students and all staff
        try{
            User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
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
        }catch (Exception e){
            RestMapBO.setErrorRestMap(restMap, e.getMessage());
            e.printStackTrace();
        }
        return RestMapBO.getRestMap();
    }

    @ResponseBody
    @RequestMapping("/auth/company/interview/add")
    public Map<String, Object> addInterview(@RequestBody CompanyApplication companyApplication, HttpSession session) {
        Map<String, Object> restMap = RestMapBO.getRestMap();
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        companyApplication.setCompanyId(user.getUserId());
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            RestMapBO.setErrorRestMap(restMap, "The interview time already exists. Please reset it");
        } else {
            companyApplication.setApplicationStatus(1);
            companyApplicationService.updateById(companyApplication);

            // Send emails to students and all staff
            try{
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
                RestMapBO.setErrorRestMap(restMap, e.getMessage());
                e.printStackTrace();
            }
        }
        return restMap;
    }

    @ResponseBody
    @RequestMapping("/auth/company/interview/new")
    public Map<String, Object> newInterviewTime(@RequestBody CompanyApplication companyApplication, HttpSession session) {
        Map<String, Object> restMap = new HashMap<>();
        User user = (User) session.getAttribute(WebConstant.SESSION_KEY_USER);
        companyApplication.setCompanyId(user.getUserId());
        boolean isExistInterviewTime = companyApplicationService.selectIsExistInterviewTime(companyApplication);
        if (isExistInterviewTime) {
            RestMapBO.setErrorRestMap(restMap, "The interview time already exists. Please reset it");
        } else {
            companyApplication.setApplicationStatus(1);
            companyApplicationService.updateById(companyApplication);
        }
        return restMap;
    }
}
