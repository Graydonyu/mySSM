<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--用百度的静态资源库的cdn安装bootstrap环境-->
<!-- Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!--font-awesome 核心CSS 文件-->
<link
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- 引入自定义样式 -->
<link href="${APP_PATH}/static/css/common.css" rel="stylesheet">
<!-- 在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!--jquery.validate-->
<!-- <script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/message.js"></script> -->
<style type="text/css">
.form {
	background: rgba(255, 255, 255, 0.2);
	width: 400px;
	margin: 200px auto;
}

#login_form {
	display: block;
}

.fa {
	display: inline-block;
	top: 27px;
	left: 6px;
	position: relative;
	color: #ccc;
}

input[type="text"], input[type="password"] {
	padding-left: 26px;
}

.checkbox {
	padding-left: 21px;
}

.form-title {
	padding-left: 35px;
}
</style>
<title>员工管理系统-登陆</title>
</head>
<body>
	<!-- 用于显示通知 -->
	<div class="alert"></div>
	<!--
			基础知识：
			网格系统:通过行和列布局
			行必须放在container内
			手机用col-xs-*
			平板用col-sm-*
			笔记本或普通台式电脑用col-md-*
			大型设备台式电脑用col-lg-*
			为了兼容多个设备，可以用多个col-*-*来控制；
		-->
	<!--
			从案例学知识，来做一个登录，注册页面
			用到font-awesome 4.3.0；bootstrap 3.3.0；jQuery Validate
		-->
	<div class="container">
		<div class="form row">
			<form class="form-horizontal col-sm-offset-2 col-md-offset-2"
				id="login_form">
				<h3 class="form-title">SSM-员工管理系统</h3>
				<div class="col-sm-10 col-md-10">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i> <input
							class="form-control required" type="text" placeholder="ManagerName"
							name="manName" autofocus="autofocus" maxlength="20" />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"></i> <input
							class="form-control required" type="password"
							placeholder="Password" name="manPassword" maxlength="20" />
					</div>
					<div class="form-group">
						<label class="checkbox"> <input type="checkbox"
							name="remember" value="1" /> Remember me
						</label>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-success pull-right"
							value="Login" id="loginButton"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery.validate.min.js"></script>
	<!-- 导入自定义的js文件 -->
	<script type="text/javascript" src="${APP_PATH }/static/js/unit.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/js/login.js"></script>
</body>
</html>