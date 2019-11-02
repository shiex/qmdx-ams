package com.qmdx.ams.mapper;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    /**
     * application count +1
     * @param userId
     * @return: void
     */
    void addApplicationCount(int userId);

    List<Student> selectAllJoinStudentTime(PageInfoBO pageInfoBO);

    int selectApplicationCountById(Integer userId);

    Student selectOneById(Integer studentId);
}
