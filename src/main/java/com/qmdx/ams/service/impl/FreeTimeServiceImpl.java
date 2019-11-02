package com.qmdx.ams.service.impl;

import com.qmdx.ams.entity.FreeTime;
import com.qmdx.ams.mapper.FreeTimeMapper;
import com.qmdx.ams.service.FreeTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FreeTimeServiceImpl extends ServiceImpl<FreeTimeMapper, FreeTime> implements FreeTimeService {
    @Override
    public int selectCountByApplicationId(Integer companyApplicationId) {
        return baseMapper.selectCountByApplicationId(companyApplicationId);
    }
}
