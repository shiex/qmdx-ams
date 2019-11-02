package com.qmdx.ams.service;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.StudentTime;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentTimeService extends IService<StudentTime> {

    List<StudentTime> selectAllByStudentId(Integer studentId);

    int selectCountByStudentId(Integer userId);

    int selectCountByStudentIdAndTime(StudentTime studentTime);
}
