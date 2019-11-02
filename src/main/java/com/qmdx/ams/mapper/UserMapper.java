package com.qmdx.ams.mapper;

import com.qmdx.ams.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

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
