package com.qmdx.ams.service.impl;

import com.qmdx.ams.entity.User;
import com.qmdx.ams.mapper.UserMapper;
import com.qmdx.ams.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User selectUserRole(String email) {
        return baseMapper.selectUserRole(email);
    }

    @Override
    public List<String> selectStaffEmailAll() {
        return baseMapper.selectStaffEmailAll();
    }

}
