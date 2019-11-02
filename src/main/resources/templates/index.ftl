<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/asserts/css/fonts.css">
    <link rel="stylesheet" href="/asserts/css/style.css">

    <style>
        .post-meta li {
            display: inline-block;
            color: #fff;
            background-color: #494c45;
            padding: 4px 6px;
            border-radius: 6px;
        }
    </style>
</head>

<body>
<#include "static_top.ftl">

<div id="slider" class="section-padding" ng-app="findApp" ng-controller="findController">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="slider-text">
                    <div class="newsletter-form">
                        <input type="text" ng-model="antistop" placeholder="Please enter company name...">
                        <button ng-click="find()">Search</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="latest-news" class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="page-title">
                    <h2>The company list</h2>
                    <p>Welcome to browse</p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12" id="company-list">
                <#if companyList ??>
                    <#list companyList as company>
                        <div class="single-post">
                            <img src="/asserts/images/img-1.jpg" class="post-thumb alignleft">
                            <h2><a href="/company/${company.companyId?c}.html" target="_blank">${company.companyName}</a>
                            </h2>
                            <p class="post-meta">
                                companyType<span
                                        style="margin: 0px 10px">&bull; </span>${company.typeOfBusiness!'not have'}
                            </p>
                            <p class="post-meta">
                                location<span style="margin: 0px 10px">&bull; </span>${company.location!'not have'}
                            </p>
                            <div class="post-meta">
                                <ul>
                                    <li style="background-color: #fff; color: #aaa; padding: 4px 0px">
                                        job vacancy<span style="margin: 0px 10px">&bull; </span>
                                    </li>
                                    <#if company.positionList ??>
                                        <#list company.positionList as position>
                                            <li>${position.positionName}</li>
                                        </#list>
                                    </#if>
                                </ul>
                            </div>
                            <a href="/company/${company.companyId?c}.html"
                               target="_blank" class="read-more" style="margin-top: 0px">
                                <i class="fa fa-angle-right"></i>view details</a>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12" style="margin-top: 20px">
                <a class="more-link"
                   companyId="${companyId?c}"
                   companyName="${companyName!'0'}"
                   isLoad="0"
                   onclick="loadCompanyData(this)">Browse more <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>

<div id="footer-top" class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="footer-left">
                    <h2>Graduation Project</h2>
                    <div class="shop-content">
                        <p>Thank you for your guidance in school. </p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="latest-tweets">
                    <h2>Student appointment interview management system</h2>
                    <div id="tweet"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="footer-area">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="copyright-text">
                    <p><i class="fa fa-heart"></i>
                        <a href="/">From a plate of potatoes Fried potatoes</a> - manufacture</br>
                        <a href="/">From a plate of potatoes Fried with potatoes</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/html" id="company-tmp">
    {{each companyList as company}}
    <div class="single-post">
        <img src="/asserts/images/img-1.jpg" class="post-thumb alignleft">
        <h2><a href="/company/{{company.companyId}}.html" target="_blank">{{company.typeOfBusiness}}</a>
        </h2>
        <p class="post-meta">
            companyType<span style="margin: 0px 10px">&bull; </span>{{company.companyType}}
        </p>
        <p class="post-meta">
            location<span style="margin: 0px 10px">&bull; </span>{{company.location}}
        </p>
        <div class="post-meta">
            <ul>
                <li style="background-color: #fff; color: #aaa; padding: 4px 0px">
                    job vacancy<span style="margin: 0px 10px">&bull; </span>
                </li>
                {{if company.positionList != null}}
                {{each company.positionList as position}}
                <li>{{position.positionName}}</li>
                {{/each}}
                {{/if}}
            </ul>
        </div>
        <a href="/company/{{company.companyId}}.html"
           target="_blank" class="read-more" style="margin-top: 0px">
            <i class="fa fa-angle-right"></i>view details
        </a>
    </div>
    {{/each}}
</script>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/webjars/angularjs/1.5.8/angular.min.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>
<script type="text/javascript" src="/asserts/js/template-web.js"></script>

<script>
    var findApp = angular.module('findApp', []);
    findApp.controller('findController', function ($scope) {
        $scope.find = function () {
            var antistop = $scope.antistop;
            location.href = "/company/find?companyName="+antistop;
        };

    });

    function loadCompanyData(e) {
        var company = {};
        company.companyId = $(e).attr('companyId');
        if ($(e).attr('companyName') != 0) {
            company.companyName = $(e).attr('companyName');
        }
        if ($(e).attr('isLoad') != 1) {
            Ax.rest('/company/load/data', company, true, function (data) {
                var companyListData = {
                    companyList: data.companyList
                }
                var companyHtml = template('company-tmp', companyListData);
                $('#company-list').append(companyHtml);
                $(e).attr('companyId', data.companyId);
                if (data.isLoad == 1) {
                    $(e).attr('isLoad', data.isLoad);
                    $(e).text('All data has been loaded');
                }
            })
        }
    }
</script>
</html>