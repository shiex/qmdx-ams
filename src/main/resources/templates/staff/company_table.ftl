<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>company table list</title>
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
                    <a href="javascript:;"><i class="layui-icon">&#xe770;</i>StudentManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a></dd>
                        <dd><a href="/auth/staff/interview/list"><i class="layui-icon">&#xe609;</i>interviewList</a></dd>
                        <dd><a href="/auth/staff/free/time/list"><i class="layui-icon">&#xe609;</i>freeTimeList</a></dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe637;</i>FreeTimeManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff//free/time"><i class="layui-icon">&#xe60e;</i>setFreeTime</a></dd>
                    </dl>
                </li>
                <<li class="layui-nav-item">
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
                <legend>Company Position Data List</legend>
            </fieldset>
            <table class="layui-hide" id="companyPositionList" lay-filter="CompanyPositionList-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

<div id="edit-dialog" style="padding: 30px; display: none">
    <form class="layui-form layui-form-pane" lay-filter="companyEdit">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">companyId</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="companyId" style="cursor:not-allowed" disabled
                       placeholder="companyId" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">companyName</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="companyName" required  lay-verify="required"
                       placeholder="companyName" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">typeOfBusiness</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="typeOfBusiness" required  lay-verify="required"
                       placeholder="typeOfBusiness" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">positionId</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="positionId" style="cursor:not-allowed" disabled
                       placeholder="companyId" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">positionName</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="positionName" required  lay-verify="required"
                       placeholder="positionName" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">numOfPosition</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="numOfPosition" required  lay-verify="required"
                       placeholder="numOfPosition" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">location</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="location" required  lay-verify="required"
                       placeholder="location" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">tel</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="tel" required  lay-verify="required"
                       placeholder="tel" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 140px">email</label>
            <div class="layui-input-block" style="margin-left: 140px">
                <input type="text" name="email" required  lay-verify="required"
                       placeholder="email" autocomplete="off" class="layui-input"
                       lay-reqtext="This field must not be empty">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn" lay-submit lay-filter="confirm">confirm</button>
            </div>
        </div>
    </form>
</div>
</body>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">edit</a>
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

            table.render({
                elem: '#companyPositionList',
                toolbar: '#topDemo',
                title: 'Company PositionList List',
                page: true,
                limit: 15,
                limits: [15, 30, 40, 50],
                url: '/auth/staff/company/table/data',
                cols: [[
                    {field: 'companyId', title: 'companyId', width: 110, sort: true, fixed: 'left'}
                    , {field: 'companyName', title: 'companyName', width: 200}
                    , {field: 'typeOfBusiness', title: 'typeOfBusiness', width: 200}
                    , {field: 'positionId', title: 'positionId', width: 150, sort: true}
                    , {field: 'positionName', title: 'positionName', width: 200}
                    , {field: 'numOfPosition', title: 'numOfPosition', width: 130, sort: true}
                    , {field: 'location', title: 'location', width: 260}
                    , {field: 'tel', title: 'tel', width: 220}
                    , {field: 'email', title: 'email', width: 170}
                    , {fixed: 'right', width: 85, align: 'center', toolbar: '#barDemo'}
                ]]
            });

        table.on('tool(CompanyPositionList-table)', function (obj) {
            var layEvent = obj.event;
            if (layEvent == 'edit') {
                layer.open({
                    type: 1,
                    title: 'be editing',
                    area: ['715px','600px'],
                    content: $('#edit-dialog'),
                    success: function (layero, index) {
                        form.val('companyEdit', obj.data);
                    },
                    cancel: function (index, layero) {
                        layer.close(index);
                    }
                });
            }
        });

        form.on('submit(confirm)', function(formData){
            Ax.rest("/auth/staff/company/table/save", formData.field, true, function (data) {
                layer.msg('modify successfully ');
            })
        });

        form.on('submit(search)', function(data){
            layer.msg(JSON.stringify(data.field));
            loadData(data.field.queryType, data.field.queryCondition);
            return false;
        });

        form.on('submit(refresh)', function(data){
            location.reload();
            return false;
        });

    });

</script>
