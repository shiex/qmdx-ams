<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>company application list</title>
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
                    <a href="/auth/company/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/auth/company/interview/list"><i class="layui-icon">&#xe60e;</i>interviewList</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>My Application List</legend>
            </fieldset>
            <table class="layui-hide" id="companyApplication" lay-filter="companyApplication-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>

    <div class="layui-form layui-form-pane" id="interview" style="display: none; padding: 40px" lay-filter="interview">
        <div class="layui-form-item" style="display: none">
            <div class="layui-input-block">
                <input type="text" name="companyApplicationId" class="layui-input">
                <input type="text" name="studentId" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="padding: 9px 0px; width: 130px;">interview address</label>
            <div class="layui-input-block" style="margin-left: 131px">
                <input type="text" name="interviewLocation" required lay-verify="required"
                       placeholder="Please enter your interview address or video"
                       autocomplete="off" class="layui-input"
                       lay-reqtext="This item must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="padding: 9px 0px; width: 130px;">interview time</label>
            <div class="layui-input-block" style="margin-left: 131px" id="studentTime-list">

            </div>
        </div>
        <div style="height: 2px; background-color: #eeeeee; margin-bottom: 20px"></div>
        <h2 style="margin-bottom: 15px">I sat between my father and mother</h2>
        <div style="height: 2px; background-color: #eeeeee; margin-bottom: 20px"></div>
        <div class="layui-block">
            <label class="layui-form-label" style="width: 160px">new interview date</label>
            <div class="layui-input-block" style="margin-left: 160px">
                <input name="newInterviewDate" type="text" autocomplete="off" id="interviewDate"
                       lay-reqtext="Mandatory fields cannot be blank"
                       class="layui-input laydate">
            </div>
        </div>
        <div class="layui-block">
            <label class="layui-form-label" style="width: 160px">new interview time</label>
            <div class="layui-input-block" style="margin-left: 160px">
                <input name="newInterviewTime" type="text" autocomplete="off" id="interviewTime"
                       lay-reqtext="Mandatory fields cannot be blank"
                       class="layui-input laydate">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 131px; margin-top: 20px; text-align:right">
                <button class="layui-btn" lay-submit lay-filter="confirm">confirm</button>
            </div>
        </div>
    </div>
    <div class="layui-form layui-form-pane" id="interview2" style="display: none; padding: 40px"
         lay-filter="interview2">
        <div class="layui-form-item" style="display: none">
            <div class="layui-input-block">
                <input type="text" name="companyApplicationId" class="layui-input">
                <input type="text" name="studentId" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="padding: 9px 0px; width: 130px;">interview address</label>
            <div class="layui-input-block" style="margin-left: 131px">
                <input type="text" name="interviewLocation" required lay-verify="required"
                       placeholder="Please enter your interview address or video"
                       autocomplete="off" class="layui-input"
                       lay-reqtext="This item must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 131px; margin-top: 20px; text-align:right">
                <button class="layui-btn" lay-submit lay-filter="confirm2">confirm</button>
            </div>
        </div>
    </div>
    <div class="layui-form layui-form-pane" id="interview3" style="display: none; padding: 40px" lay-filter="interview3">
        <div class="layui-form-item" style="display: none">
            <div class="layui-input-block">
                <input type="text" name="companyApplicationId" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="padding: 9px 0px; width: 160px;">interview address</label>
            <div class="layui-input-block" style="margin-left: 160px">
                <input type="text" name="interviewLocation" required lay-verify="required"
                       placeholder="Please enter your interview address or video"
                       autocomplete="off" class="layui-input"
                       lay-reqtext="Mandatory fields cannot be blank">
            </div>
        </div>
        <div class="layui-block">
            <label class="layui-form-label" style="width: 160px">interview date</label>
            <div class="layui-input-block" style="margin-left: 160px">
                <input name="newInterviewDate" type="text" autocomplete="off" id="interviewDate2"
                       lay-reqtext="Mandatory fields cannot be blank" required lay-verify="required"
                       class="layui-input laydate">
            </div>
        </div>
        <div class="layui-block">
            <label class="layui-form-label" style="width: 160px">interview time</label>
            <div class="layui-input-block" style="margin-left: 160px">
                <input name="newInterviewTime" type="text" autocomplete="off" id="interviewTime2"
                       lay-reqtext="Mandatory fields cannot be blank" required lay-verify="required"
                       class="layui-input laydate">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 131px; margin-top: 20px; text-align:right">
                <button class="layui-btn" lay-submit lay-filter="confirm3">confirm</button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/html" id="studentTime-tmp">
    <select name="interviewTime">
        <option value="">The following time is the student's free time</option>
        {{each studentTimeList as studentTime}}
        <option value="{{studentTime.freeTime}}">{{studentTime.freeTime}}</option>
        {{/each}}
    </select>
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
            element = layui.element,
            laydate = layui.laydate;

        table.render({
            elem: '#companyApplication',
            title: 'Company Application List',
            page: true,
            limit: 15,
            limits: [15, 30, 40, 50],
            url: '/auth/company/application/data',
            where: {
                status: null,
            },
            cols: [[
                {field: 'companyApplicationId', title: '#', sort: true, width: 150}
                , {
                    field: 'studentName', title: 'studentName', width: 150,
                    templet: function (data) {
                        return data.student.studentName;
                    }
                }
                , {
                    field: 'applicationStatus', title: 'status', width: 170,
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
                    field: 'positionName', title: 'positionName', width: 200
                    , templet: function (data) {
                        return data.position.positionName;
                    }
                }
                , {
                    fixed: 'right', title: 'operation', width: 165, align: 'center'
                    , templet: function (data) {
                        var btn = '#';
                        if (data.applicationStatus == 0) {
                            btn = '<div class="layui-form">'
                                + '<button class="layui-btn layui-btn-xs" lay-submit lay-filter="accept" '
                                + 'companyApplicationId=' + data.companyApplicationId
                                + ' studentId=' + data.studentId
                                + '>accept</button></div>'
                        }
                        if (data.applicationStatus == 6) {
                            btn = '<div class="layui-form">'
                                + '<button class="layui-btn layui-btn-xs" lay-submit lay-filter="accept2" '
                                + 'companyApplicationId=' + data.companyApplicationId
                                + ' studentId=' + data.studentId
                                + '>accept</button><button class="layui-btn layui-btn-xs" lay-submit lay-filter="refuse"'
                                + 'companyApplicationId=' + data.companyApplicationId
                                + '>refuse</button></div>'
                        }
                        return btn;
                    }
                }
            ]]
        });

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

        var interviewDate2 = laydate.render({
            elem: '#interviewDate2',
            type: 'date',
            btns: ['confirm'],
            min: 0,
            max: 7,
        });

        var interviewTime2 = laydate.render({
            elem: '#interviewTime2',
            type: 'time',
            btns: ['confirm'],
            min: '08:00:00',
            max: '17:30:00',
        });

        form.on('submit(accept)', function (formData) {
            var companyApplicationId = $(this).attr('companyApplicationId');
            var studentId = $(this).attr('studentId');
            Ax.rest('/auth/company/student/time/list', companyApplicationId, true, function (data) {
                layer.open({
                    type: 1,
                    title: 'Enter the interview format and select the interview time',
                    area: ['585px', '495px'],
                    content: $('#interview'),
                    success: function (layero, index) {
                        form.val("interview", {
                            "companyApplicationId": companyApplicationId,
                            "interviewLocation": null,
                            "studentId": studentId
                        });
                        var studentTimeListData = {
                            studentTimeList: data
                        }
                        var studentTimeHtml = template('studentTime-tmp', studentTimeListData);
                        $('#studentTime-list').empty();
                        $('#studentTime-list').append(studentTimeHtml);
                        form.render('select');
                    },
                    cancel: function (index, layero) {
                        layer.close(index);
                        return false;
                    }
                });
            })
            return false;
        });

        form.on('submit(confirm)', function (formData) {
            var data = formData.field;
            var companyApplication = {};
            companyApplication.companyApplicationId = data.companyApplicationId;
            companyApplication.studentId = data.studentId;
            companyApplication.interviewLocation = data.interviewLocation;
            if (data.interviewTime == '') {
                if (data.newInterviewDate == '' || data.newInterviewTime == '') {
                    layer.msg('Please select or enter the interview time first');
                    return
                } else {
                    companyApplication.interviewTime = data.newInterviewDate + ' ' + data.newInterviewTime;
                }
            } else {
                if (data.newInterviewDate == '' && data.newInterviewTime == '') {
                    companyApplication.interviewTime = data.interviewTime;
                } else {
                    layer.msg('Your operation is wrong');
                    return;
                }
            }
            Ax.rest("/auth/company/interview/add", companyApplication, true, function (data) {
                layer.closeAll();
                layer.msg('The student application has been approved, waiting for the student to confirm the interview time')
            })
            return false;
        });

        form.on('submit(accept2)', function (formData) {
            var companyApplicationId = $(this).attr('companyApplicationId');
            var studentId = $(this).attr('studentId');
            layer.open({
                type: 1,
                title: 'Type in the interview model -- video or type in the interview address',
                area: ['585px', '235px'],
                content: $('#interview2'),
                success: function (layero, index) {
                    form.val("interview2", {
                        "companyApplicationId": companyApplicationId,
                        "interviewLocation": null,
                        "studentId": studentId
                    });
                },
                cancel: function (index, layero) {
                    layer.close(index);
                    return false;
                }
            });
        });

        form.on('submit(confirm2)', function (formData) {
            Ax.rest("/auth/company/appointment/add", formData.field, true, function (data) {
                location.reload();
            })
            return false;
        });

        form.on('submit(refuse)', function (formData) {
            var companyApplicationId = $(this).attr('companyApplicationId');
            layer.open({
                type: 1,
                title: 'Redefine appointment times',
                area: ['585px', '285px'],
                content: $('#interview3'),
                success: function (layero, index) {
                    form.val("interview3", {
                        "companyApplicationId": companyApplicationId,
                        "interviewLocation": null
                    });
                },
                cancel: function (index, layero) {
                    layer.close(index);
                    return false;
                }
            });
        });

        form.on('submit(confirm3)', function (formData) {
            var data = formData.field;
            var companyApplication = {};
            companyApplication.companyApplicationId = data.companyApplicationId;
            companyApplication.interviewLocation = data.interviewLocation;
            companyApplication.interviewTime = data.newInterviewDate + ' ' + data.newInterviewTime;
            Ax.rest("/auth/company/interview/new", companyApplication, true, function (data) {
                location.reload();
            })
            return false;
        });

    });
</script>
