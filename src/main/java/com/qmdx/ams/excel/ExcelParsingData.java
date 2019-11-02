package com.qmdx.ams.excel;

import com.qmdx.ams.entity.*;

public class ExcelParsingData {

    private ExcelCompanyPosition excelCompanyPosition;
    private ExcelStudentAdvisor excelStudentAdvisor;

    private User user;
    private UserRole userRole;
    private Student student;
    private Advisor generalAdvisor;
    private Advisor projectAdvisor;
    private StudentGeneralAdvisor studentGeneralAdvisor;
    private StudentProjectAdvisor studentProjectAdvisor;
    private Company company;
    private Position position;
    private CompanyPosition companyPosition;

    public ExcelParsingData(ExcelCompanyPosition excelCompanyPosition) {
        this.excelCompanyPosition = excelCompanyPosition;
    }

    public ExcelParsingData(ExcelStudentAdvisor excelStudentAdvisor) {
        this.excelStudentAdvisor = excelStudentAdvisor;
    }

    public User getStudentUser() {
        user = new User();
        user.setUserId(excelStudentAdvisor.getStudentId());
        user.setUserName(excelStudentAdvisor.getStudentName());
        user.setEmail(excelStudentAdvisor.getStudentEmail());
        user.setPassword("123");
        return user;
    }

    public UserRole getStudentUserRole() {
        userRole = new UserRole();
        userRole.setUserId(excelStudentAdvisor.getStudentId());
        userRole.setRoleId(1);
        return userRole;
    }

    public Student getStudent() {
        student = new Student();
        student.setStudentId(excelStudentAdvisor.getStudentId());
        student.setStudentName(excelStudentAdvisor.getStudentName());
        student.setStudentEmail(excelStudentAdvisor.getStudentEmail());
        return student;
    }

    public Advisor getGeneralAdvisor() {
        generalAdvisor = new Advisor();
        generalAdvisor.setAdvisorId(excelStudentAdvisor.getGeneralAdvisorId());
        generalAdvisor.setAdvisorName(excelStudentAdvisor.getGeneralAdvisorName());
        generalAdvisor.setAdvisorEmail(excelStudentAdvisor.getGeneralAdvisorEmail());
        generalAdvisor.setAdvisorType(false);
        return generalAdvisor;
    }

    public Advisor getProjectAdvisor() {
        projectAdvisor = new Advisor();
        projectAdvisor.setAdvisorId(excelStudentAdvisor.getProjectAdvisorId());
        projectAdvisor.setAdvisorName(excelStudentAdvisor.getProjectAdvisorName());
        projectAdvisor.setAdvisorEmail(excelStudentAdvisor.getProjectAdvisorEmail());
        projectAdvisor.setAdvisorType(true);
        return projectAdvisor;
    }

    public StudentGeneralAdvisor getStudentGeneralAdvisor() {
        studentGeneralAdvisor = new StudentGeneralAdvisor();
        studentGeneralAdvisor.setStudentId(excelStudentAdvisor.getStudentId());
        studentGeneralAdvisor.setGeneralAdvisorId(excelStudentAdvisor.getGeneralAdvisorId());
        return studentGeneralAdvisor;
    }

    public StudentProjectAdvisor getStudentProjectAdvisor() {
        studentProjectAdvisor = new StudentProjectAdvisor();
        studentProjectAdvisor.setStudentId(excelStudentAdvisor.getStudentId());
        studentProjectAdvisor.setProjectAdvisorId(excelStudentAdvisor.getProjectAdvisorId());
        return studentProjectAdvisor;
    }

    public User getCompanyUser() {
        user = new User();
        user.setUserId(excelCompanyPosition.getCompanyId());
        user.setUserName(excelCompanyPosition.getCompanyName());
        user.setEmail(excelCompanyPosition.getEmail());
        user.setPassword("123");
        return user;
    }

    public UserRole getCompanyUserRole() {
        userRole = new UserRole();
        userRole.setUserId(excelCompanyPosition.getCompanyId());
        userRole.setRoleId(2);
        return userRole;
    }

    public Company getCompany() {
        company = new Company();
        company.setCompanyId(excelCompanyPosition.getCompanyId());
        company.setCompanyName(excelCompanyPosition.getCompanyName());
        company.setTypeOfBusiness(excelCompanyPosition.getTypeOfBusiness());
        company.setTel(excelCompanyPosition.getTel());
        company.setLocation(excelCompanyPosition.getLocation());
        company.setEmail(excelCompanyPosition.getEmail());
        return company;
    }

    public Position getPosition() {
        position = new Position();
        position.setPositionId(excelCompanyPosition.getPositionId());
        position.setPositionName(excelCompanyPosition.getPositionName());
        position.setNumOfPosition(excelCompanyPosition.getNumOfPosition());
        return position;
    }

    public CompanyPosition getCompanyPosition() {
        companyPosition = new CompanyPosition();
        companyPosition.setCompanyId(excelCompanyPosition.getCompanyId());
        companyPosition.setPositionId(position.getPositionId());
        return companyPosition;
    }


}
