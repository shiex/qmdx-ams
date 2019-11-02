package com.qmdx.ams.service.impl;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.StudentTime;
import com.qmdx.ams.mapper.StudentTimeMapper;
import com.qmdx.ams.service.StudentTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTimeServiceImpl extends ServiceImpl<StudentTimeMapper, StudentTime> implements StudentTimeService {
    @Override
    public List<StudentTime> selectAllByStudentId(Integer studentId) {
        return baseMapper.selectAllByStudentId(studentId);
    }

    @Override
    public int selectCountByStudentId(Integer userId) {
        return baseMapper.selectCountByStudentId(userId);
    }

    @Override
    public int selectCountByStudentIdAndTime(StudentTime studentTime) {
        return baseMapper.selectCountByStudentIdAndTime(studentTime);
    }
}
