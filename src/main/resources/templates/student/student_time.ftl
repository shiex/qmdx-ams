<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>free time page</title>
    <link rel="stylesheet" href="/asserts/layui/css/layui.css">

    <style>
        i {
            margin-right: 10px;
        }

        .layui-block {
            width: 600px;
            margin-bottom: 20px;
            margin-left: 30px;
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
                    <a href="/auth/student/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/auth/student/interview/list"><i class="layui-icon">&#xe609;</i>InterviewList</a>
                </li>
                <li class="layui-nav-item layui-this">
                    <a href="/auth/student/free/time"><i class="layui-icon">&#xe60e;</i>SelectFreeTime</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>Set free time for students</legend>
            </fieldset>
            <div id="sch-form" class="layui-form layui-form-pane">
                <#if freeTime ??>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">Optional time period</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${startDate} ${startTime} To ${endDate} ${endTime}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The free date</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input name="freeDate" type="text" autocomplete="off" id="freeDate"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The free time</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input name="freeTime" type="text" autocomplete="off" id="freeTime"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block" style="text-align: right;">
                        <button class="layui-btn" lay-submit lay-filter="add">add</button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="reset">reset</button>
                    </div>
                <#else>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">Optional time period</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="Wait for administrator Settings, cannot add temporarily">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The free date</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input type="text" autocomplete="off" style="cursor:not-allowed" disabled
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The free time</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input type="text" autocomplete="off" style="cursor:not-allowed" disabled
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block" style="text-align: right;">
                        <button class="layui-btn" lay-submit lay-filter="sendMessage">Notify the administrator to set idle time</button>
                    </div>
                </#if>
            </div>
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

<script>
    layui.use(['element', 'laydate', 'layer', 'form'], function () {
        var laydate = layui.laydate,
            layer = layui.layer,
            form = layui.form,
            element = layui.element;

        var freeDate = laydate.render({
            elem: '#freeDate',
            type: 'date',
            btns: ['confirm'],
            min: '${startDate?date}',
            max: '${endDate?date}'
        });

        var freeTime = laydate.render({
            elem: '#freeTime',
            type: 'time',
            btns: ['confirm'],
            min: '${startTime?time}',
            max: '${endTime?time}'
        });

        // add
        form.on('submit(add)', function (data) {
            var obj = data.field;
            var studentTime = {};
            studentTime.companyApplicationId = obj.companyApplicationId;
            studentTime.freeTime = obj.freeDate + " " + obj.freeTime;
            Ax.rest("/auth/student/free/time/add", studentTime, true, function (data) {
                alert('successfully added');
                location.reload();
            });
            return false;
        });

        // reset
        form.on('submit(reset)', function (data) {
            location.reload();
            return false;
        });

        // reset
        form.on('submit(sendMessage)', function (data) {
            Ax.rest("/auth/student/send/message", null, true, function (data) {
                layer.msg('A notification message has been sent to the administrator');
            })
            return false;
        });
    });

</script>
