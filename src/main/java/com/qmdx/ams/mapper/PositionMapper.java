package com.qmdx.ams.mapper;

import com.qmdx.ams.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PositionMapper extends BaseMapper<Position> {
    /**
     * Number of applications +1
     * @param positionId
     * @return: void
     */
    void addApplicationCount(int positionId);
}
