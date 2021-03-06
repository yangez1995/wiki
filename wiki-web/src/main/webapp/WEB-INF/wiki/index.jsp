<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/public.css" rel="stylesheet">
<link href="../resources/css/wiki/index.css" rel="stylesheet">
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/js/public.js"></script>
<script src="../resources/js/wiki/index.js"></script>
<script src="../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/js/util.js"></script>
<script src="../resources/js/util/timeUtil.js"></script>
<title>Wiki</title>
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
		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
			<div class="input-group" style="margin-top: 0px;">
			    <input type="text" id="search" class="form-control">
			    <span class="input-group-btn">
			        <button class="btn btn-default" type="button" onclick="resultPage()">搜索</button>
			    </span>
			</div>
		</div>
		<button class="btn btn-primary" id="new-wiki">新建词条</button>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<hr style="border-color: #CCC;"/>	
		</div>
	</div>
	<div class="row">
		<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9" id="left-part" style="border-right: #CCC solid 1px;"></div>
		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></div>
	</div>
</div>
</body>
</html>