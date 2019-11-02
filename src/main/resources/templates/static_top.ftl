<nav class="navbar navbar-default" role="navigation" style="margin: 0px; background-color: #fff; border: 0px">
    <div class="container" style="padding: 0px">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse" style="margin-top: 25px">
                    <span class="sr-only">Switch navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="logo">
                    <h1><a href="/" style="color: #8ACA4A;">Diploma project</a></h1>
                </div>
            </div>
            <div class="mainmenu">
                <div class="navbar-collapse collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <#if user ??>
                            <#if user.role.role == 'student'>
                                <li><a href="/auth/${user.role.role}/index">Student personal centre</a></li>
                            </#if>
                            <#if user.role.role == 'company'>
                                <li><a href="/auth/${user.role.role}/index">company manage</a></li>
                            </#if>
                            <#if user.role.role == 'staff'>
                                <li><a href="/auth/${user.role.role}/index">staff manage</a></li>
                            </#if>
                            <li>
                                <a>${user.userName}</a>
                            </li>
                            <li>
                                <a href="/login.out">login out</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/login.html">login</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>