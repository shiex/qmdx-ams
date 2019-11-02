package com.qmdx.ams.service;

import com.qmdx.ams.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PositionService extends IService<Position> {
    /**
     * Number of applications +1
     * @param positionId
     * @return: void
     */
    void addApplicationCount(int positionId);
}
