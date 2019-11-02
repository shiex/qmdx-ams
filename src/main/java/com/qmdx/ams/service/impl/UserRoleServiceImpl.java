package com.qmdx.ams.service.impl;

import com.qmdx.ams.entity.UserRole;
import com.qmdx.ams.mapper.UserRoleMapper;
import com.qmdx.ams.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
