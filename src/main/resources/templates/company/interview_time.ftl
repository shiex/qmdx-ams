<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>interview time page</title>
    <link rel="stylesheet" href="/asserts/layui/css/layui.css">

    <style>
        i {
            margin-right: 10px;
        }

        .layui-block {
            width: 500px;
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
                    <a href="javascript:;"><i class="layui-icon">&#xe770;</i>StudentManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                        </dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>data
                                operation</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>data
                                operation</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><i class="layui-icon">&#xe637;</i>TimeManage</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a href="/auth/staff/free/time">
                                <i class="layui-icon">&#xe60e;</i>add free time</a>
                        </dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe60e;</i>InterviewTime</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe63c;</i>TableManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/table/import"><i class="layui-icon">&#xe62d;</i>ImportTable</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe62d;</i>ExportTable</a></dd>
                    </dl>
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
                <#if companyApplication ??>
                    <div class="layui-block" style="width: 500px; margin-bottom: 20px;">
                        <label class="layui-form-label">application form id</label>
                        <div class="layui-input-block">
                            <input name="companyApplicationId" style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.companyApplicationId?c}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">student name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.student.studentName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">company name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.company.companyName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">position name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.position.positionName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free date</label>
                        <div class="layui-input-block">
                            <input name="freeDate" type="text" autocomplete="off" id="freeDate"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free time start</label>
                        <div class="layui-input-block">
                            <input name="startTime" type="text" autocomplete="off" id="startTime"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free time end</label>
                        <div class="layui-input-block">
                            <input name="endTime" type="text" autocomplete="off" id="endTime"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block" style="text-align: right;">
                        <button class="layui-btn" lay-submit lay-filter="add">
                            add
                        </button>
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="reset">
                            reset
                        </button>
                    </div>
                <#else>
                    <div class="layui-block" style="width: 500px; margin-bottom: 20px;">
                        <label class="layui-form-label">application form id</label>
                        <div class="layui-input-block">
                            <input name="companyApplicationId" style="cursor:not-allowed" disabled
                                   class="layui-input" placeholder="Please set the time in the application list interface"
                                   lay-verify="required" lay-reqtext="Please set the time in the application list interface">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">student name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">company name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">position name</label>
                        <div class="layui-input-block">
                            <input style="cursor:not-allowed" disabled class="layui-input">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free date</label>
                        <div class="layui-input-block">
                            <input name="freeDate" type="text" autocomplete="off" id="freeDate"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   style="cursor:not-allowed" disabled
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free time start</label>
                        <div class="layui-input-block">
                            <input name="startTime" type="text" autocomplete="off" id="startTime"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   style="cursor:not-allowed" disabled
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label">The free time end</label>
                        <div class="layui-input-block">
                            <input name="endTime" type="text" autocomplete="off" id="endTime"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   style="cursor:not-allowed" disabled
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block" style="text-align: right;">
                        <a class="layui-btn" href="/auth/staff/index">Click to</a>
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
            min: 0,
            max: 7,
        });

        var startTime = laydate.render({
            elem: '#startTime',
            type: 'time',
            btns: ['confirm'],
            min: '08:00:00',
            max: '17:30:00',
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date,
                    hours: date.hours,
                    minutes: date.minutes,
                    seconds: date.seconds
                };
            }
        })

        var endTime = laydate.render({
            elem: '#endTime',
            type: 'time',
            btns: ['confirm'],
            min: '08:00:00',
            max: '17:30:00',
            done: function (value, date) {
                startTime.config.max = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date,
                    hours: date.hours,
                    minutes: date.minutes,
                    seconds: date.seconds
                }

            }
        })

        // add
        form.on('submit(add)', function (data) {
            var freeTime = data.field;
            Ax.rest("/auth/staff/free/time/add", freeTime, true, function (data) {
                layer.msg('Free time period has been added. Only one free time period can be set by a single application form');
            });
            return false;
        });

        // reset
        form.on('submit(reset)', function (data) {
            location.reload();
            return false;
        });

    });

</script>
