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
<body>
	<!-- updateModal -->
	<div class="modal fade" id="updateEmpModal" tabindex="-1" role="dialog"
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
							<label for="inputEmail3" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="name" class="form-control" id="updateName" name="empName"
									placeholder="Name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="updateEmail" name="empEmail"
									placeholder="Email@QQ.com">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Gender</label>
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
							<label for="inputEmail3" class="col-sm-2 control-label">Department</label>
							<div class="col-sm-4">
								<select class="form-control" id="updateDepOptions" name="depId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateEmp">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- addModal -->
	<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog"
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
							<label for="inputEmail3" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="name" class="form-control" id="inputName" name="empName"
									placeholder="Name">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputEmail" name="empEmail"
									placeholder="Email@QQ.com">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Gender</label>
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
							<label for="inputEmail3" class="col-sm-2 control-label">Department</label>
							<div class="col-sm-4">
								<select class="form-control" id="depOptions" name="depId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveEmp">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8 text-right">
				<button type="button" class="btn btn-primary" id="addEmp">新增</button>
				<button type="button" class="btn btn-danger" id="deleEmp">删除</button>
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
			<div class="col-md-6" id="page_nav"></div>
		</div>
	</div>
	
	<%-- <script type="text/javascript" src="${APP_PATH }/static/js/jquery-validate.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${APP_PATH }/static/js/jquery-validate.js"></script> --%>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<!-- 导入自定义的js文件 -->
	<script type="text/javascript" src="${APP_PATH }/static/js/employee/employee.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/js/unit.js"></script>

</body>
</html>