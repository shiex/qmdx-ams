package com.qmdx.ams.service.impl;

import com.qmdx.ams.bo.PageInfoBO;
import com.qmdx.ams.entity.Company;
import com.qmdx.ams.mapper.CompanyMapper;
import com.qmdx.ams.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Override
    public List<Company> selectDropDownList(Company company) {
        return baseMapper.selectDropDownList(company);
    }

    @Override
    public Company selectCompanyPosition(Integer companyId) {
        return baseMapper.selectCompanyPosition(companyId);
    }

    @Override
    public String selectCompanyEmaliOne(Integer companyId) {
        return baseMapper.selectCompanyEmaliOne(companyId);
    }
}
