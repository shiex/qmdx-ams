package com.qmdx.ams.service;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qmdx.ams.excel.ExcelStudentAdvisor;

import java.util.List;

public interface StudentService extends IService<Student> {

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
