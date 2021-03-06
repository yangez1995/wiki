<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../../resources/css/public.css" rel="stylesheet">
<link href="../../resources/css/wiki/compare.css" rel="stylesheet">
<script src="../../resources/js/jquery-3.2.1.min.js"></script>
<script src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../../resources/js/public.js"></script>
<script src="../../resources/js/wiki/compare.js"></script>
<title>compare</title>
</head>
<body>
<nav class="navbar navbar-default" id="nav-top" role="navigation" style="background: #7FB80E;">
	<div class="container">
    <div class="navbar-header">
        <a class="navbar-brand" href="#" style="color: #FCFCFC;">Wiki</a>
    </div>
    <ul class="nav navbar-nav navbar-right" style="margin-right: 30px;"> 
        <li><a href=""><span class="glyphicon glyphicon-user"></span> 注册</a></li> 
        <li><a onclick="userLogin()"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
    </ul>
    </div>
</nav>
<div class="container">
<div class="row">
	<table class="table edit-msg">
	<thead>
	<tr>
	    <th>更新时间</th>
	    <th>编写人</th>
	    <th>修改原因</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	    <td id="update-time"></td>
	    <td id="update-by"></td>
	    <td>暂无</td>
	</tr>	
	</tbody>
	</table>
</div>
<div class="row" id="compare-start">
	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 compare-box">
		<div class="box-title" style="background: #FFD4D4;">-</div>
	</div>
	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 compare-box">
		<div class="box-title" style="background: #C1E3FF;">+</div>
	</div>
</div>
<div class="row" id="delete-insert">
	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 compare-box" id="delete-compare-box">
	</div>
	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 compare-box" id="insert-compare-box">
	</div>
</div>
</div>
</body>
</html>