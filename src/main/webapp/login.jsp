<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>管理员登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />
	
	<%
		pageContext.setAttribute("APP_PATH",request.getContextPath());
	%>

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="${APP_PATH}/static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="${APP_PATH}/static/css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="${APP_PATH}/static/css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="${APP_PATH}/static/css/magnific-popup.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="${APP_PATH}/static/css/Register_login_style.css">
	
		<!-- jQuery -->
	<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>

	<!-- Modernizr JS -->
	<script src="${APP_PATH}/static/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	</head>
	<body>
	<header id="gtco-header" class="gtco-cover" role="banner">
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					

					<div class="row row-mt-15em">
						<div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
							<span class="intro-text-small">Welcome to CQYT</span>
							<h1>欢迎来到邮电大学移通学院</h1>	
						</div>
						<div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu">
										<li class="active gtco-first"><a href="#" data-tab="signup"><b>注册</b></a></li>
										<li class="gtco-second"><a href="#" data-tab="login"><b>登录</b></a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-content-inner active" data-content="signup">
<!-- 											<form action="#">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">学号 :</label>
														<input type="text" class="form-control" id="UserID">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">昵称 :</label>
														<input type="password" class="form-control" id="UserName1">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password2">密码 :</label>
														<input type="password" class="form-control" id="password1">
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="submit" class="btn btn-primary" value="注册">
													</div>
												</div>
											</form>	 -->
										</div>

										<div class="tab-content-inner" data-content="login">
											<form action="#">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">学号 :</label>
														<input type="text" class="form-control" id="UserID">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">密码 :</label>
														<input type="password" class="form-control" id="password">
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="submit" class="btn btn-primary" value="登录">
													</div>
												</div>
											</form>	
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
							
					
				</div>
			</div>
		</div>
	</header>

	</div>

	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery Easing -->
	<script src="${APP_PATH}/static/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="${APP_PATH}/static/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="${APP_PATH}/static/js/jquery.waypoints.min.js"></script>
	<!-- countTo -->
	<script src="${APP_PATH}/static/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="${APP_PATH}/static/js/jquery.magnific-popup.min.js"></script>
	<script src="${APP_PATH}/static/js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="${APP_PATH}/static/js/login_main.js"></script>

	</body>
</html>