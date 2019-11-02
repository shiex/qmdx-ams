<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.1/css/bootstrap.min.css">

    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: -webkit-box;
            display: flex;
            -ms-flex-align: center;
            -ms-flex-pack: center;
            -webkit-box-align: center;
            align-items: center;
            -webkit-box-pack: center;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>

<body class="text-center">
<div class="form-signin" ng-app="loginApp" ng-controller="loginController">
    <img class="mb-4" src="asserts/images/ams.png" alt="" width="112" height="112">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <#if authorityErrorMsg ??>
        <p style="color: red;">${authorityErrorMsg}</p>
    </#if>
    <label class="sr-only">Email</label>
    <input type="text" class="form-control" ng-model="email" placeholder="Email" required="" autofocus="">
    <label class="sr-only">Password</label>
    <input type="password" class="form-control" ng-model="password" placeholder="Password" required="">
    <div class="checkbox mb-6"></div>
    <button class="btn btn-lg btn-primary btn-block" ng-click="login()">Sign in</button>
    <div class="checkbox mb-1"></div>
    <p class="mt-3 mb-3 text-muted">
        <a href="/"> © tourist online experience </a>
    </p>
</div>

</body>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/webjars/angularjs/1.5.8/angular.min.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>

<script>

    var loginApp = angular.module('loginApp', []);
    loginApp.controller('loginController', function($scope) {
        $scope.login = function() {
            var req = {};
            req.email = $scope.email;
            req.password = $scope.password;
            Ax.rest("/login.do", req, true, function (data) {
                location.href = "/";
            });
        };
    });

    /*   Enter triggers an event that simulates clicking login   */
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $('.btn-lg').click();
        }
    });
</script>
</html>