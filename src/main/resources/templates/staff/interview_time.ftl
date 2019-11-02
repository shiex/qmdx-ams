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
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>Set interview time for students</legend>
            </fieldset>
            <div id="sch-form" class="layui-form layui-form-pane">
                <#if companyApplication ??>
                    <div class="layui-block" style="width: 500px; margin-bottom: 20px;">
                        <label class="layui-form-label" style="width: 160px">application form id</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input name="companyApplicationId" style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.companyApplicationId?c}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">company id</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   name="companyId" value="${companyApplication.companyId?c}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">student name</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.student.studentName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">company name</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.company.companyName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">position name</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input style="cursor:not-allowed" disabled class="layui-input"
                                   value="${companyApplication.position.positionName}">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The interview date</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input name="interviewDate" type="text" autocomplete="off" id="interviewDate"
                                   lay-verify="required" lay-reqtext="Mandatory fields cannot be blank"
                                   class="layui-input laydate">
                        </div>
                    </div>
                    <div class="layui-block">
                        <label class="layui-form-label" style="width: 160px">The interview time</label>
                        <div class="layui-input-block" style="margin-left: 160px">
                            <input name="interviewTime" type="text" autocomplete="off" id="interviewTime"
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

        var interviewDate = laydate.render({
            elem: '#interviewDate',
            type: 'date',
            btns: ['confirm'],
            min: 0,
            max: 7,
        });

        var interviewTime = laydate.render({
            elem: '#interviewTime',
            type: 'time',
            btns: ['confirm'],
            min: '08:00:00',
            max: '17:30:00',
        });

        // add
        form.on('submit(add)', function (data) {
            var companyApplication = {};
            companyApplication.companyApplicationId = data.field.companyApplicationId;
            companyApplication.companyId = data.field.companyId;
            companyApplication.interviewTime = data.field.interviewDate + " " + data.field.interviewTime;
            Ax.rest("/auth/staff/interview/time/add", companyApplication, true, function (data) {
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
