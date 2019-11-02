package com.qmdx.ams.service.impl;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Company;
import com.qmdx.ams.entity.CompanyApplication;
import com.qmdx.ams.mapper.CompanyApplicationMapper;
import com.qmdx.ams.service.CompanyApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyApplicationServiceImpl extends ServiceImpl<CompanyApplicationMapper, CompanyApplication> implements CompanyApplicationService {
    @Override
    public List<CompanyApplication> selectPageHelperList(PageInfoBO pageInfoBO) {
        return baseMapper.selectPageHelperList(pageInfoBO);
    }

    @Override
    public CompanyApplication selectOneJoin(Integer companyApplicationId) {
        return baseMapper.selectOneJoin(companyApplicationId);
    }

    @Override
    public int selectApplicationStatus(Integer companyApplicationId) {
        return baseMapper.selectApplicationStatus(companyApplicationId);
    }

    @Override
    public String selectStudentEmail(Integer companyApplicationId) {
        return baseMapper.selectStudentEmail(companyApplicationId);
    }

    @Override
    public boolean selectIsExistInterviewTime(CompanyApplication companyApplication) {
        System.out.println(baseMapper.selectIsExistInterviewTime(companyApplication));
        return baseMapper.selectIsExistInterviewTime(companyApplication) != 0;
    }

    @Override
    public void updateStatusById(CompanyApplication companyApplication) {
        baseMapper.updateStatusById(companyApplication);
    }

    @Override
    public void updateInterviewTimeById(CompanyApplication companyApplication) {
        baseMapper.updateInterviewTimeById(companyApplication);
    }
}
