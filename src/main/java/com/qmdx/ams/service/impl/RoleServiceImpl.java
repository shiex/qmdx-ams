package com.qmdx.ams.service.impl;

import com.qmdx.ams.entity.Role;
import com.qmdx.ams.mapper.RoleMapper;
import com.qmdx.ams.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
