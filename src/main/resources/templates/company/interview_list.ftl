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
                <li class="layui-nav-item">
                    <a href="/auth/company/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                </li>
                <li class="layui-nav-item layui-this">
                    <a href="/auth/company/interview/list"><i class="layui-icon">&#xe60e;</i>interviewList</a>
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

        table.render({
            elem: '#studentInterview',
            title: 'student Interview List',
            page: true,
            limit: 15,
            limits: [15, 30, 40, 50],
            url: '/auth/company/application/data',
            where: {
                status: 2,
            },
            cols: [[
                {field: 'companyApplicationId', title: 'applicationId', width: 130, sort: true, fixed: 'left'}
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
                , {
                    field: 'positionName', title: 'positionName', width: 200
                    , templet: function (data) {
                        return data.position.positionName;
                    }
                }
            ]]
        });

    });

</script>
