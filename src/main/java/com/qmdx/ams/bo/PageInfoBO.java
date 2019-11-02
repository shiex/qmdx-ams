package com.qmdx.ams.bo;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class PageInfoBO {

    private Integer page;  // 起始页码数
    private Integer limit; // 查询多少条数据
    private Integer applicationStatus; // 申请状态
    private String  queryCondition; // 查询条件，模糊查询，如果不存在则查询全部
    // 查询类型，如果不存在则查询全部
    // studentId：按学生ID，
    // studentName：按学生名称，
    // companyName：按公司名称，
    // positionname：按职位名称
    // student：查询学生页面的申请列表（以学生ID） - 反之查询所有申请列表，员工界面
    // company：查询公司页面的申请列表（以公司ID） - 反之查询所有申请列表，员工界面
    private String queryType;
    private Integer total;
    private List list;

    private PageInfoBO(){
    }

    public PageInfoBO(int page, int limit) {
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public PageInfoBO(String queryType, String queryCondition, int page, int limit) {
        this.queryType = queryType;
        this.queryCondition = queryCondition;
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public PageInfoBO(int applicationStatus, int page, int limit) {
        this.applicationStatus = applicationStatus;
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public PageInfoBO(int applicationStatus, String queryType, int page, int limit) {
        this.applicationStatus = applicationStatus;
        this.queryType = queryType;
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public PageInfoBO(int applicationStatus, String queryType, String queryCondition, int page, int limit) {
        this.applicationStatus = applicationStatus;
        this.queryType = queryType;
        this.queryCondition = queryCondition;
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
