package com.qmdx.ams.service.impl;

import com.qmdx.ams.entity.Position;
import com.qmdx.ams.mapper.PositionMapper;
import com.qmdx.ams.service.PositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    @Override
    public void addApplicationCount(int positionId) {
        this.baseMapper.addApplicationCount(positionId);
    }
}
