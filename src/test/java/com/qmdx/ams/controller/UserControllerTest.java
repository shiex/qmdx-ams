package com.qmdx.ams.controller;

import com.qmdx.ams.entity.User;
import com.qmdx.ams.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author shiex-薛
 * @title: UserControllerTest
 * @projectName qmdx-ams
 * @description: TODO
 * @date 2019\10\20 002018:59
 */
public class UserControllerTest {

    private static Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Resource
    private UserService userService;

    /**
     * 模拟用户登录
     *
     * @param
     * @return: void
     */
    @Test
    public void login() {
        String loginMessage = loginTest("email","password");
        log.info(loginMessage);
    }

    public String loginTest(String email, String password){
        User user = userService.selectUserRole(email);
        if(user == null){
            return "The user does not exist";
        }else {
            if(user.getPassword().equals(password)){
                return "login successfully";
            }else {
                return "wrong password";
            }
        }
    }
}