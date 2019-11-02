package com.qmdx.ams.mapper;

import com.qmdx.ams.entity.FreeTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface FreeTimeMapper extends BaseMapper<FreeTime> {

    /**
     * Enquiry on number of applications
     * @param companyApplicationId
     * @return: int
     */
    int selectCountByApplicationId(Integer companyApplicationId);
}
