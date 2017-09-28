<%--<%@ page import="cn.czu.t1.controler.AdminController" %>--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台客服管理系统</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.min.css">
    <script type="text/javascript" src="/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        var token = window.localStorage.getItem("ics_token");
        $.ajax({
            url: "/handle/admin/checkLogin",
            data: {},
            type: "POST",
            beforeSend: function(xhr){xhr.setRequestHeader('token', token);},//这里设置header
            success: function(res) {
                if(res.status == "1"){
                    token = res.token;
                    window.localStorage.setItem('ics_token', res.token);
                }else{
                    window.location.href = '/admin/login';
                }
            },
            dataType:"JSON"
        });
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin">智能客服后台管理系统</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-user"></span> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">账号</a></li>
                        <li><a href="#">设置</a></li>
                        <li><a href="#">注销</a></li>
                    </ul>
                </li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>