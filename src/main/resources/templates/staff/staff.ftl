<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>staff page</title>
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
                        <dd class="layui-this"><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                        </dd>
                        <dd><a href="/auth/staff/interview/list"><i class="layui-icon">&#xe609;</i>interviewList</a>
                        </dd>
                        <dd><a href="/auth/staff/free/time/list"><i class="layui-icon">&#xe609;</i>freeTimeList</a></dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>DataOperation</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>DataOperation</a>
                        </dd>
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
                <legend>Student Application List</legend>
            </fieldset>
            <table class="layui-hide" id="companyApplication" lay-filter="companyApplication-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

<div id="appointment-dialog" style="padding: 10px;height: 230px; display: none; position: relative">
    <form class="layui-form layui-form-pane" lay-filter="appointment">
        <div class="layui-form-item" style="display: none">
            <div class="layui-input-block" style="margin-left: 0px">
                <input type="text" name="companyApplicationId" style="cursor:not-allowed" disabled
                       placeholder="companyApplicationId" class="layui-input">
                <input type="text" name="companyId" style="cursor:not-allowed" disabled
                       placeholder="companyId" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" id="appointment-list">

        </div>
        <div class="layui-form-item" style="position: absolute; bottom: 10px; right: 30px">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn" lay-submit lay-filter="confirm">confirm</button>
            </div>
        </div>
    </form>
</div>
</body>

<script type="text/html" id="studentTime-tmp">
    <div class="layui-input-block" style="margin-left: 0px">
        {{each studentTimeList as studentTime}}
        <input type="radio" name="interviewTime" value="{{studentTime.freeTime}}" title="{{studentTime.freeTime}}">
        {{/each}}
    </div>
</script>

<script type="text/html" id="barDemo">
    <button class="layui-btn layui-btn-xs" lay-event="SelectAvailableTime">SelectAvailableTime</button>
    <button class="layui-btn layui-btn-xs" lay-event="SetInterviewTime">SetInterviewTime</button>
</script>

<script type="text/html" id="topDemo">
    <div class="layui-form" action="">
        <div class="layui-form-item" style="margin: 0px; padding-left: 15px">
            <div class="layui-input-inline">
                <a class="layui-btn layui-btn-primary" href="/auth/staff/index">refresh</a>
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
                <input type="text" name="queryCondition" lay-verify="required"
                       lay-reqtext="Please enter the query criteria first"
                       placeholder="Enter query criteria" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="search">search</button>
            </div>
        </div>
    </div>
</script>

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
                elem: '#companyApplication',
                toolbar: '#topDemo',
                title: 'Company Application List',
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
                    , {field: 'studentId', title: 'studentId', width: 110, sort: true}
                    , {
                        field: 'studentName', title: 'studentName', width: 130,
                        templet: function (data) {
                            return data.student.studentName;
                        }
                    }
                    , {
                        field: 'applicationStatus', title: 'status', width: 190,
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
                            return "none";
                        }
                    }
                    , {
                        field: 'interviewTime', title: 'appointmentTime', width: 150,
                        templet: function (data) {
                            if (data.applicationStatus == 1) return data.interviewTime;
                            if (data.applicationStatus == 6) return data.interviewTime;
                            return "none";
                        }
                    }
                    , {field: 'academicYear', title: 'academicYear', width: 120}
                    , {field: 'cooperationPlan', title: 'cooperationPlan', width: 140}
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
                    , {
                        field: 'numOfPosition', title: 'numOfPosition', width: 130, sort: true
                        , templet: function (data) {
                            return data.position.numOfPosition;
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
                    , {fixed: 'right', width: 285, align: 'center', toolbar: '#barDemo'}
                ]]
            });
        }

        // Listen for line tool events
        table.on('tool(companyApplication-table)', function (obj) {
            var text = "No idle or interview time can be set";
            var layEvent = obj.event;
            var companyApplicationId = obj.data.companyApplicationId;
            if (layEvent == 'SelectAvailableTime') {
                Ax.rest("/auth/staff/application/status", companyApplicationId, true, function (data) {
                    if (data == 1) {
                        layer.msg("This application is currently in appointment status," + text);
                    } else if (data == 2) {
                        layer.msg("This application is currently in interview status," + text);
                    } else if (data == 3) {
                        layer.msg("The application is currently in employment status," + text);
                    } else if (data == 4) {
                        layer.msg("This application is currently in the status of administrator operation," + text);
                    } else if (data == 5) {
                        layer.msg("This application is currently in the status of administrator operation," + text);
                    } else if (data == 6) {
                        layer.msg("This application is currently in appointment status," + text);
                    } else {
                        var formData = obj.data;
                        Ax.rest("/auth/staff/appointment/time/list", formData.studentId, true, function (data) {
                            layer.open({
                                type: 1,
                                title: 'Set appointment time',
                                area: ['715px', '300px'],
                                content: $('#appointment-dialog'),
                                success: function (layero, index) {
                                    form.val('appointment', formData);
                                    var studentTimeListData = {
                                        studentTimeList: data
                                    }
                                    var studentTimeHtml = template("studentTime-tmp", studentTimeListData);
                                    $('#appointment-list').empty();
                                    $('#appointment-list').append(studentTimeHtml);
                                    form.render('radio');
                                },
                                cancel: function (index, layero) {
                                    layer.close(index);
                                }
                            });
                        })
                    }
                })
            }
            if (layEvent == 'SetInterviewTime') {
                Ax.rest("/auth/staff/application/status", companyApplicationId, true, function (data) {
                    if (data == 2) {
                        layer.msg("This application is currently in interview status," + text);
                    } else if (data == 3) {
                        layer.msg("The application is currently in employment status," + text);
                    } else if (data == 4) {
                        layer.msg("This application is currently in the status of administrator operation," + text);
                    } else {
                        window.open("/auth/staff/interview/time/" + companyApplicationId);
                    }
                })
            }
        });

        // Query button
        form.on('submit(search)', function (data) {
            loadData(null, data.field.queryType, data.field.queryCondition);
            return false;
        });

        form.on('submit(confirm)', function (data) {
            if (data.field.interviewTime == null || data.field.interviewTime == ''){
                layer.msg('Please select the appointment time first');
                return;
            } else {
                Ax.rest("/auth/staff/appointment/time/add", data.field, true, function (data) {
                    layer.closeAll();
                    layer.msg('successfully added');
                })
            }
        });

        loadData(null, null, null);

    });

</script>
