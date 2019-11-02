<div class="layui-header">
    <div class="layui-logo" style="width: 400px; margin-left: 20px; text-align: left">
        <i class="layui-icon" style="margin-right: 10px">&#xe68e;</i>
        <#if user ??>
            <#if user.role.role == 'student'>
                student personal center
            </#if>
            <#if user.role.role == 'company'>
                company personal center
            </#if>
            <#if user.role.role == 'staff'>
                AMS Backstage Management System
            </#if>
        <#else>
            <li>
                <a href="/login.html">login</a>
            </li>
        </#if>
    </div>
    <ul class="layui-nav layui-layout-right" style="margin-right: 100px">
        <li class="layui-nav-item">
            <a href="javascript:;">
                ${user.userName}
            </a>
            <dl class="layui-nav-child">
                <dd><a href="/">Go to the home page</a></dd>
                <dd><a href="/login.out">loginOut</a></dd>
            </dl>
        </li>
    </ul>
</div>