<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/public.css" rel="stylesheet">
<link href="../resources/css/admin/index.css" rel="stylesheet"> 
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/js/elementAuth.js"></script>
<script src="../resources/js/admin/index.js"></script>
<script src="../resources/plugin/echarts.min.js"></script>
<script src="../resources/plugin/layer/layer.js"></script>
<script src="../resources/js/validate.js"></script>
<script src="../resources/js/customLayer.js"></script>
<script src="../resources/js/util/timeUtil.js"></script>
<title>admin</title>
</head>
<body lang="zh-CN">
<div class="container-fluid" style="padding: 0px;">
  <div style="width: 100%; height: 50px; background: #556;">
    <div class="hidden-xs" style="width: 200px; height: 100%; float: left;border-right: #667 solid 1px;">
      <img src="../resources/image/default-head.png" style="width: 30px;height: 30px;margin-left: 10px;margin-top: 10px;border-radius: 15px;float: left;">
      <div style="float: left;margin-left: 10px;margin-top: 6px;width: 100px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
        <span id="on-login-name" style="color: #EEE;">用户昵称</span><br/>
        <span id="on-login-role" style="color: #CCC;font-size: 11px;">角色名</span>
      </div>
      <span style="color: #EEE;line-height: 50px;cursor: pointer;" onMouseOver="this.style.color='#CCC'" onMouseOut="this.style.color='#EEE'" onclick="logout()">[注销]</span>
    </div>
    <div class="hidden-xs input-group" style="float: right;width: 240px;margin-top: 8px;margin-right: 20px;margin-left: 20px;">
      <input class="form-control" id="easy-search" type="text" style="background: #667; border-color: #445;color: #FCFCFC;">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" onclick="easySearch()" style="background: #667; border-color: #445;color: #DDD">搜索</button>
      </span>
    </div>
    
    <div class="hidden-sm hidden-md hidden-lg" style="width: 200px; height: 100%; float: left;">
      <img src="../resources/image/default-head.png" style="width: 30px;height: 30px;margin-left: 10px;margin-top: 10px;border-radius: 15px;float: left;">
      <div style="float: left;margin-left: 10px;margin-top: 6px;width: 100px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
        <span id="on-login-name" style="color: #EEE;">用户昵称</span><br/>
        <span id="on-login-role" style="color: #CCC;font-size: 11px;">角色名</span>
      </div>
    </div>
    
    <button type="button" class="hidden-sm hidden-md hidden-lg navbar-toggle" id="min-nav-btn" data-toggle="collapse" data-target="#left-nav-collapse">
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
  </div>
  <div class="hidden-sm hidden-md hidden-lg collapse navbar-collapse" id="left-nav-collapse" style="margin: 0px;padding: 0px;">
  	<div style="height: 50px;background: #556;">
  		<button type="button" class="btn btn-default" onclick="logout()" style="background: #889;border: 0px;color: #EEE;margin-top: 9px;margin-left: 10px;float: left;">注销</button>
  		<div class="input-group" style="float: right;width: 200px;margin-top: 8px;margin-right: 10px;margin-left: 20px;">
	      <input class="form-control" id="easy-search-min" type="text" style="background: #667; border-color: #445;color: #FCFCFC;">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button" onclick="easySearchMin()" style="background: #667; border-color: #445;color: #DDD">搜索</button>
	      </span>
	    </div>
  	</div>
  	<dl class="nav-dl dl-act">
      <dt>用户管理</dt>
      <dd id="u-account-min">· 用户帐号管理</dd>
      <sec:authorize url="/admin/user/role/delete">
      	<dd id="u-role-min">· 用户角色管理</dd>
      </sec:authorize>
      <dd id="u-msg-min">· 用户信息管理</dd>
      <dd id="u-chart-min">· 用户图表</dd>
      <!-- <dd id="au-chart">· 用户活跃图表</dd> -->
    </dl>
    <dl class="nav-dl">
      <dt>角色管理</dt>
      <dd id="r-msg-min">· 角色信息管理</dd>
      <dd id="r-auth-min">· 角色权限管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>权限管理</dt>
      <dd id="auth-msg-min">· 权限信息管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>资源管理</dt>
      <dd id="res-msg-min">· 资源信息管理</dd>
      <dd id="res-type-min">· 资源类型管理</dd>
      <dd id="res-auth-min">· 资源权限管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>词条管理</dt>
      <dd id="wiki-msg-min">· 词条信息管理</dd>
      <dd id="wiki-category-min">· 词条类型管理</dd>
    </dl>
  </div>
  <div class="hidden-xs" id="left-nav" style="width: 200px; float:left; background: #667;">
    <dl class="nav-dl dl-act">
      <dt>用户管理</dt>
      <dd id="u-account">· 用户帐号管理</dd>
      <sec:authorize url="/admin/user/role/delete">
      	<dd id="u-role">· 用户角色管理</dd>
      </sec:authorize>
      <dd id="u-msg">· 用户信息管理</dd>
      <dd id="u-chart">· 用户图表</dd>
      <!-- <dd id="au-chart">· 用户活跃图表</dd> -->
    </dl>
    <dl class="nav-dl">
      <dt>角色管理</dt>
      <dd id="r-msg">· 角色信息管理</dd>
      <dd id="r-auth">· 角色权限管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>权限管理</dt>
      <dd id="auth-msg">· 权限信息管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>资源管理</dt>
      <dd id="res-msg">· 资源信息管理</dd>
      <dd id="res-type">· 资源类型管理</dd>
      <dd id="res-auth">· 资源权限管理</dd>
    </dl>
    <dl class="nav-dl">
      <dt>词条管理</dt>
      <dd id="wiki-msg">· 词条信息管理</dd>
      <dd id="wiki-category">· 词条类型管理</dd>
    </dl>
  </div>
  <security-element url="/admin/index/**">securityElementTest1</security-element>
  <security-element url="/admin/index/**">securityElementTest11</security-element>
  <security-element url="/admin/user/role/getPage">securityElementTest2</security-element>
  <security-element url="/admin/user/role">securityElementTest3</security-element>
  <div id="content" style="float: left;padding: 20px;"></div>
</div>
</body>
</html>