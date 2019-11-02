package com.qmdx.ams.controller;


import com.qmdx.ams.bo.RestMapBO;
import com.qmdx.ams.constant.WebConstant;
import com.qmdx.ams.entity.User;
import com.qmdx.ams.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login.html")
    public String loginPage() {
        return "login";
    }

    /**
     * User Login
     *
     * @param u
     * @param session
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public Map<String, Object> login(@RequestBody User u, HttpSession session) {
        Map<String, Object> restMap = RestMapBO.getRestMap();

        User user = userService.selectUserRole(u.getEmail());
        if (user == null) {
            RestMapBO.setErrorRestMap(restMap, "The user does not exist");
            return restMap;
        }
        if (!user.getPassword().equals(u.getPassword())) {
            RestMapBO.setErrorRestMap(restMap, "wrong password");
            return restMap;
        }

        session.setAttribute(WebConstant.SESSION_KEY_USER, user);
        return restMap;
    }

    /**
     * User logout
     *
     * @param session
     * @return: java.lang.String
     */
    @RequestMapping("/login.out")
    public String loginOut(HttpSession session) {
        session.removeAttribute(WebConstant.SESSION_KEY_USER);
        return "/";
    }
}
