package com.qmdx.ams.service.impl;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Student;
import com.qmdx.ams.mapper.StudentMapper;
import com.qmdx.ams.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public void addApplicationCount(int userId) {
        baseMapper.addApplicationCount(userId);
    }

    @Override
    public List<Student> selectAllJoinStudentTime(PageInfoBO pageInfoBO) {
        return baseMapper.selectAllJoinStudentTime(pageInfoBO);
    }

    @Override
    public int selectApplicationCountById(Integer userId) {
        return baseMapper.selectApplicationCountById(userId);
    }

    @Override
    public Student selectOneById(Integer studentId) {
        return baseMapper.selectOneById(studentId);
    }
}
