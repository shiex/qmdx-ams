<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>student interview list</title>
    <link rel="stylesheet" href="/asserts/layui/css/layui.css">

    <style>
        i {
            margin-right: 10px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "../static_top2.ftl">

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><i class="layui-icon">&#xe770;</i>StudentManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a></dd>
                        <dd class="layui-this"><a href="/auth/staff/interview/list"><i class="layui-icon">&#xe609;</i>interviewList</a></dd>
                        <dd><a href="/auth/staff/free/time/list"><i class="layui-icon">&#xe609;</i>freeTimeList</a></dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe637;</i>FreeTimeManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff//free/time"><i class="layui-icon">&#xe60e;</i>setFreeTime</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe63c;</i>TableManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/table/import"><i class="layui-icon">&#xe62d;</i>operationTable</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>Student Interview List</legend>
            </fieldset>
            <table class="layui-hide" id="studentInterview" lay-filter="studentInterview-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

<script type="text/html" id="topDemo">
    <div class="layui-form" action="">
        <div class="layui-form-item" style="margin: 0px; padding-left: 15px">
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-primary" href="/auth/staff/interview/list">refresh</a>
            </div>
            <div class="layui-input-inline">
                <select name="queryType" lay-verify="required" lay-reqtext="Please select the query type first">
                    <option value="">Select query type</option>
                    <option value="studentId">studentId</option>
                    <option value="studentName">studentName</option>
                    <option value="companyName">companyName</option>
                    <option value="positionName">positionName</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <input type="text" name="queryCondition" lay-verify="required" lay-reqtext="Please enter the query criteria first"
                       placeholder="Enter query criteria" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="search">search</button>
            </div>
        </div>
    </div>
</script>

</body>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/asserts/layui/layui.all.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>
<script type="text/javascript" src="/asserts/js/template-web.js"></script>

<script>
    layui.use(['element', 'table', 'form', 'layer'], function () {
        var table = layui.table,
            form = layui.form,
            layer = layui.layer,
            element = layui.element;

        function loadData(status, queryType, queryCondition) {
            table.render({
                elem: '#studentInterview',
                toolbar: '#topDemo',
                title: 'student Interview List',
                page: true,
                limit: 15,
                limits: [15, 30, 40, 50],
                url: '/auth/staff/student/application/data',
                where: {
                    status: status,
                    queryType: queryType,
                    queryCondition: queryCondition
                },
                cols: [[
                    {field: 'companyApplicationId', title: 'applicationId', width: 130, sort: true, fixed: 'left'}
                    ,{field: 'studentId', title: 'studentId', width: 110, sort: true}
                    , {
                        field: 'studentName', title: 'studentName', width: 200,
                        templet: function (data) {
                            return data.student.studentName;
                        }
                    }
                    , {field: 'interviewTime', title: 'interviewTime', width: 170}
                    , {field: 'interviewLocation', title: 'interviewLocation', width: 170}
                    , {field: 'academicYear', title: 'academicYear', width: 170}
                    , {field: 'cooperationPlan', title: 'cooperationPlan', width: 220}
                    , {field: 'companyId', title: 'companyId', width: 110, sort: true}
                    , {
                        field: 'companyName', title: 'companyName', width: 200
                        , templet: function (data) {
                            return data.company.companyName;
                        }
                    }
                    , {
                        field: 'typeOfBusiness', title: 'typeOfBusiness', width: 200
                        , templet: function (data) {
                            return data.company.typeOfBusiness;
                        }
                    }
                    , {
                        field: 'positionName', title: 'positionName', width: 200
                        , templet: function (data) {
                            return data.position.positionName;
                        }
                    }
                ]]
            });
        }

        // Listen for line tool events
        table.on('tool(studentInterview-table)', function (obj) {
            location.href = "/auth/staff/free/time/" + obj.data.companyApplicationId;
        });

        // Query button
        form.on('submit(search)', function (data) {
            loadData(2, data.field.queryType, data.field.queryCondition);
            return false;
        });

        // refresh page
        form.on('submit(refresh)', function (data) {
            location.reload();
            return false;
        });

        loadData(2, null, null);

    });

</script>
