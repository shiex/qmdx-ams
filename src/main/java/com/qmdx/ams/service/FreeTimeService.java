package com.qmdx.ams.service;

import com.qmdx.ams.entity.FreeTime;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FreeTimeService extends IService<FreeTime> {

    /**
     * Enquiry on number of applications
     * @param companyApplicationId
     * @return: int
     */
    int selectCountByApplicationId(Integer companyApplicationId);
}
