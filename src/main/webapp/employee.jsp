<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工列表</title>
<!-- web路径问题 
	不以/开头的相对路径，找资源以当前资源路径为基准，容易出问题
	以/开头的相对路径，找资源以服务器路径为基准（http：//localshost：8080），需要加上项目名
 -->
<%
	/* 获取服务器根目录，以/开头不以/结尾 */
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 引入jQuery -->
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>
</head>

<!-- 引入Bootstrap -->
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<style type="text/css">
		body {
			background: url(static/img/8.jpg) no-repeat;background-size: cover;font-size: 16px;
		}
	</style>
<body>
	<!-- updateModal -->
	<div class="modal fade" id="updateEmpModal" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="updateModalLabel">更新员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="updateEmpForm">
						<div class="form-group">
							<label for="updateName" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="name" class="form-control" id="updateName" name="empName"
									autofocus="autofocus" placeholder="Name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="updateEmail" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="updateEmail" name="empEmail"
									placeholder="Email@QQ.com">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="empSex" id="updateRadio1" value="男">
									男
								</label> <label class="radio-inline"> <input type="radio"
									name="empSex" id="updateRadio2" value="女">
									女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="updateDepOptions" class="col-sm-2 control-label">Department</label>
							<div class="col-sm-4">
								<select class="form-control" id="updateDepOptions" name="depId">
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="updateEmp">更新</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- addModal -->
	<div class="modal fade" id="addEmpModal" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addModalLabel">新增员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addEmpForm">
						<div class="form-group">
							<label for="inputName" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="name" class="form-control required" id="inputName" name="empName"
									autofocus="autofocus" placeholder="Name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="email" class="form-control required" id="inputEmail" name="empEmail"
									placeholder="Email@QQ.com">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="empSex" id="inlineRadio1" value="男" checked="checked">
									男
								</label> <label class="radio-inline"> <input type="radio"
									name="empSex" id="inlineRadio2" value="女">
									女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="depOptions" class="col-sm-2 control-label">Department</label>
							<div class="col-sm-4">
								<select class="form-control" id="depOptions" name="depId">
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="saveEmp">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>SSM-CRUD</h1>
			</div>
			<div class="col-md-4 text-center">
				<h1>员工列表</h1>
			</div>
			<div class="col-md-4 text-right">
				<ul class="list-inline" style="margin-top:15px;">
  					<li><h4><b>manager</b></h4></li>
  					<li><a>退出</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<a href="${APP_PATH }/department.jsp"><button type="button" class="btn text-primary">部门管理</button></a>
			</div>
			<div class="col-md-8 text-right">
				<form class="form-inline">				
					<div class="input-group">
						<input type="text" class="form-control" id="search_val" placeholder="可根据员工名、部门名、性别、邮箱查询">
						<div class="input-group-addon" id="search"><i class="glyphicon glyphicon-search"></i></div>
					</div>					
					<button type="button" class="btn btn-primary" id="addEmp">新增</button>
					<button type="button" class="btn btn-danger" id="deleEmp">删除</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped table-hover" id="emp_table">
					<thead>
						<tr>
							<th class="text-center">
								<input type="checkbox" id="check_all"/>
							</th>
							<th class="text-center">#</th>
							<th class="text-center">empName</th>
							<th class="text-center">email</th>
							<th class="text-center">gender</th>
							<th class="text-center">deptName</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody id="emp_tbody">

					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" id="page_msg"></div>
			<div class="col-md-6 text-right" id="page_nav"></div>
		</div>
	</div>
	
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery.validate.min.js"></script>
	<!-- 导入自定义的js文件 -->
	<script type="text/javascript" src="${APP_PATH }/static/js/employee/employee.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/js/unit.js"></script>

</body>
</html>