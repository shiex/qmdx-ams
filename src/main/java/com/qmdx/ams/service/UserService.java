package com.qmdx.ams.service;

import com.qmdx.ams.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * Query the user and the corresponding role
     * @param email
     * @return: com.qmdx.ams.entity.User
     */
    User selectUserRole(String email);

    /**
     * Check all employee email addresses
     * @param
     * @return: java.util.List<java.lang.String>
     */
    List<String> selectStaffEmailAll();

}
