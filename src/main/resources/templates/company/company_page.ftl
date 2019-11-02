<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>company page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/asserts/css/fonts.css">
    <link rel="stylesheet" href="/asserts/css/style.css">
</head>

<body ng-app="applyForApp" ng-controller="applyForController">

<#include "../static_top.ftl">

<div id="slider" class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <div class="slider-text">
                    <h2>${company.companyName}</h2>
                    <h4> &amp; company introduce</h4>
                    <p>${company.introduce!'There is no information about this company'}</p>
                </div>
            </div>

            <div class="col-md-7">
                <div class="layout-img text-right">
                    <img class="img-responsive" src="/asserts/images/img-9.jpg" style="width: 500px; height: 240px">
                </div>
            </div>
        </div>
    </div>
</div>

<div id="projects" class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="page-title">
                    <h2>The position being advertised</h2>
                    <p>Apply as soon as you see the right job for you !!!</p>
                </div>
            </div>
        </div>

        <div class="row">
            <#if positionList ??>
                <#list positionList as position>
                    <div class="col-md-4 col-sm-5">
                        <div class="single-project">
                            <div class="project-link">
                                <h4>${position.positionName}</h4>
                                <p>invite numbers：${position.numOfPosition?c}</p>
                                <#if user ??>
                                    <#if user.role.role == 'student'>
                                        <a class="project-detail" style="cursor: pointer"
                                           ng-click="application('${position.positionId?c}', '${position.positionName}')">
                                            <i class="fa fa-angle-right">apply for</i>
                                        </a>
                                    </#if>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>

        <div class="row">
            <div class="col-md-12 text-center">
                <label class="more-link" style="cursor: context-menu">Welcome to apply for</label>
            </div>
        </div>
    </div>
</div>

<div class="footer-area">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="footer-menu">
                    <ul>
                        <li><a>typeOfBusiness：${company.typeOfBusiness!''}</a></li>
                        <li><a>tel：${company.tel!''}</a></li>
                        <li><a>email：${company.email!''}</a></li>
                        <li><a>location：${company.location!''}</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="margin-top: 20px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Please select your own free time first</h4>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" href="/auth/student/free/time" target="_blank">
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Go ahead and choose your free time
                </a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="margin-top: 20px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Please choose your free time</h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display: none">
                    <input ng-model="positionId" class="form-control" placeholder="positionId">
                </div>
                <div class="form-group">
                    <label for="txt_departmentname">companyName</label>
                    <input ng-model="companyName" style="cursor:not-allowed" disabled class="form-control" laceholder="companyName">
                </div>
                <div class="form-group">
                    <label for="txt_departmentname">positionName</label>
                    <input ng-model="positionName" style="cursor:not-allowed" disabled class="form-control"
                           placeholder="positionName">
                </div>
                <div class="form-group">
                    <label for="txt_departmentname">Please enter academic year</label>
                    <input ng-model="academicYear" class="form-control" placeholder="academicYear">
                </div>
                <div class="form-group">
                    <label for="txt_parentdepartment">Please enter the cooperation plan</label>
                    <input ng-model="cooperationPlan" class="form-control" placeholder="cooperationPlan">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>close
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="addApplyFor()">
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>affirm
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/webjars/angularjs/1.5.8/angular.min.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>

<script>
    var applyForApp = angular.module('applyForApp', []);
    applyForApp.controller('applyForController', function ($scope) {
        $scope.application = function (positionId, positionName) {
            Ax.rest('/auth/student/applyfor', null, false, function (data) {
                if (data == 0) {
                    $('#myModal').modal();
                } else {
                    $scope.positionId = positionId;
                    $scope.positionName = positionName;
                    $scope.companyName = '${company.companyName}';
                    $('#myModal2').modal();
                }
            })
        };
        $scope.addApplyFor = function () {
            var req = {
                positionId: $scope.positionId,
                academicYear: $scope.academicYear,
                cooperationPlan: $scope.cooperationPlan,
                companyId: ${company.companyId?c},
                position:{
                    positionName: $scope.positionName
                },
                company: {
                    companyName: '${company.companyName}'
                }
            };
            if (req.academicYear == null || req.cooperationPlan == null) {
                alert('academicYear/cooperationPlan Must not be empty!');
                return;
            }
            Ax.rest("/auth/student/applyfor/add", req, true, function (data) {
                alert('application approved!');
            });
        };
    });
</script>
</html>