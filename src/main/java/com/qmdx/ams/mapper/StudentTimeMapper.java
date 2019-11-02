package com.qmdx.ams.mapper;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.StudentTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface StudentTimeMapper extends BaseMapper<StudentTime> {

    List<StudentTime> selectAllByStudentId(Integer studentId);

    int selectCountByStudentId(Integer userId);

    int selectCountByStudentIdAndTime(StudentTime studentTime);
}
