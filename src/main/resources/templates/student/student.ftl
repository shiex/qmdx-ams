<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>student personal center</title>
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
                <li class="layui-nav-item layui-this">
                    <a href="/auth/student/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/auth/student/interview/list"><i class="layui-icon">&#xe609;</i>InterviewList</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/auth/student/free/time"><i class="layui-icon">&#xe60e;</i>SelectFreeTime</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>My Application List</legend>
            </fieldset>
            <table class="layui-hide" id="studentApplication" lay-filter="studentApplication-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">select free time</a>
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

        table.render({
            elem: '#studentApplication',
            title: 'Student Application List',
            page: true,
            limit: 15,
            limits: [15, 30, 40, 50],
            url: '/auth/student/application/data',
            where: {
                status: null,
            },
            cols: [[
                {field: 'companyApplicationId', title: '#', sort: true}
                , {
                    field: 'companyName', title: 'companyName', width: 200
                    , templet: function (data) {
                        return data.company.companyName;
                    }
                }
                , {
                    field: 'positionName', title: 'positionName', width: 200
                    , templet: function (data) {
                        return data.position.positionName;
                    }
                }
                , {
                    field: 'data', title: 'status', width: 150,
                    templet: function (data) {
                        var status;
                        if (data.applicationStatus == 0) status = "applying";
                        if (data.applicationStatus == 1) status = "appointment";
                        if (data.applicationStatus == 2) status = "interview";
                        if (data.applicationStatus == 3) status = "hire";
                        if (data.applicationStatus == 4) status = "interview (staff operation)";
                        if (data.applicationStatus == 5) status = "wait staff dispose";
                        if (data.applicationStatus == 6) status = "appointment (staff operation)";
                        return status;
                    }
                }
                , {
                    field: 'interviewTime', title: 'interviewTime', width: 120,
                    templet: function (data) {
                        if (data.applicationStatus == 2) return data.interviewTime;
                        if (data.applicationStatus == 4) return data.interviewTime;
                        return "not have";
                    }
                }
                , {
                    field: 'interviewTime', title: 'appointmentTime', width: 150,
                    templet: function (data) {
                        if (data.applicationStatus == 1) return data.interviewTime;
                        if (data.applicationStatus == 6) return data.interviewTime;
                        return "not have";
                    }
                }
                , {field: 'academicYear', title: 'academicYear', width: 170}
                , {field: 'cooperationPlan', title: 'cooperationPlan', width: 220}
                , {
                    field: 'typeOfBusiness', title: 'typeOfBusiness', width: 200
                    , templet: function (data) {
                        return data.company.typeOfBusiness;
                    }
                }
                , {
                    field: 'location', title: 'location', width: 220
                    , templet: function (data) {
                        return data.company.location;
                    }
                }
                , {
                    field: 'tel', title: 'tel', width: 220
                    , templet: function (data) {
                        return data.company.tel;
                    }
                }
                , {
                    field: 'email', title: 'email', width: 170
                    , templet: function (data) {
                        return data.company.email;
                    }
                }
                , {
                    fixed: 'right', width: 165, align: 'center'
                    , templet: function (data) {
                        var btn;
                        if (data.applicationStatus == 1) {
                            btn = '<div><button class="layui-btn layui-btn-xs" onclick="accept(this)"'
                                + 'companyApplicationId=' + data.companyApplicationId + '>accept</button>'
                                + '<button class="layui-btn layui-btn-xs" onclick="refuse(this)"'
                                + 'companyApplicationId=' + data.companyApplicationId + '>refuse</button></div>';
                        } else {
                            btn = '<a class="layui-btn-primary layui-btn-xs">wait for notification</a>';
                        }
                        return btn;
                    }
                }
            ]]
        });

        table.on('tool(studentApplication-table)', function (obj) {
            location.href = "/auth/student/free/time/" + obj.data.companyApplicationId;
        });

        form.on('submit(refresh)', function (data) {
            location.reload();
            return false;
        });

    });

    function accept(e) {
        var companyApplication = {};
        companyApplication.companyApplicationId = $(e).attr('companyApplicationId');
        Ax.rest("/auth/student/interview/accept", companyApplication, true, function (data) {
            location.reload();
            layer.msg('Has agreed to');
        })
    }

    function refuse(e) {
        var companyApplication = {};
        companyApplication.companyApplicationId = $(e).attr('companyApplicationId');
        Ax.rest("/auth/student/interview/refuse", companyApplication, true, function (data) {
            location.reload();
            layer.msg('Has been rejected');
        })
    }
</script>
