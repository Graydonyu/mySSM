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
	<!-- Modal -->
	<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增员工</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
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
							<th class="text-center">#</th>
							<th class="text-center">empName</th>
							<th class="text-center">email</th>
							<th class="text-center">gender</th>
							<th class="text-center">deptName</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" id="page_msg"></div>
			<div class="col-md-6" id="page_nav"></div>
		</div>
	</div>

	<script type="text/javascript">
		var toatlRecord;
	
		$(function() {
			to_page(1);

			//点击打开模态框
			$("#addEmp").on("click", function() {

				$.ajax({
					url : "${APP_PATH}/Dep/deps",
					type : "get",
					success : function(result) {

						build_dep_options(result);

					}
				})

				$("#addEmpModal").modal({
					backdrop : false
				});
			})

			//保存员工信息
			$("#saveEmp").on("click", function() {
				$.ajax({
					url : "${APP_PATH}/Employee/addEmp",
					data : $("#addEmpModal form").serialize(),
					type : "post",
					success : function(result){
						if(result.code == 100){
							//关闭模态框
							$("#addEmpModal").modal('toggle');
							
							//跳转到最后一页
							//page只要大于最后一页就会自动查询最后一页
							to_page(toatlRecord);
						};
					}
				})
			})
		})

		function to_page(page) {
			$.ajax({
				url : "${APP_PATH}/Employee/emps",
				data : "page=" + page,
				type : "get",
				success : function(result) {

					build_table_data(result);

					build_page_msg(result);

					build_page_nav(result);
				}
			})
		}

		//构建模态框部门选项
		function build_dep_options(result) {
			$("#depOptions").empty();

			$.each(result.extend.deps, function(index, item) {
				var option = $("<option></option>").append(item.depName).attr(
						"value", item.depId);
				option.appendTo("#depOptions");
			})
		}

		//构建分页条
		function build_page_nav(result) {
			$("#page_nav").empty();

			var pageInfo = result.extend.pageInfo;

			var nav = $("<nav></nav>");
			var ul = $("<ul></ul>").attr("class", "pagination");

			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));

			var fristPageLi = $("<li></li>").append($("<a></a>").append("首页"));
			var endPageLi = $("<li></li>").append($("<a></a>").append("尾页"));

			if (!pageInfo.isFirstPage) {
				ul.append(fristPageLi);
				fristPageLi.on("click", function() {
					to_page(1);
				})
			}

			if (pageInfo.hasPreviousPage) {
				ul.append(lastPageLi);
				lastPageLi.on("click", function() {
					to_page(pageInfo.pageNum - 1);
				})
			}

			$.each(pageInfo.navigatepageNums, function(index, item) {

				var numLi = $("<li></li>").append($("<a></a>").append(item));

				if (pageInfo.pageNum == item) {
					numLi.attr("class", "active");
				}

				numLi.on("click", function() {

					to_page(item);

				})

				ul.append(numLi);

			})

			if (!pageInfo.isLastPage) {
				ul.append(endPageLi);
				endPageLi.on("click", function() {
					to_page(pageInfo.pages);
				})
			}

			if (pageInfo.hasNextPage) {
				ul.append(nextPageLi);
				nextPageLi.on("click", function() {
					to_page(pageInfo.pageNum + 1);
				})
			}

			nav.append(ul).appendTo("#page_nav");

		}

		//构建分页信息
		function build_page_msg(result) {
			$("#page_msg").empty();

			var pageMsg = result.extend.pageInfo;

			$("#page_msg").append(
					"当前第" + pageMsg.pageNum + "页，总" + pageMsg.pages + "页，总"
							+ pageMsg.total + "条记录");
			
			toatlRecord = pageMsg.total;

		}

		//解析数据填充表格
		function build_table_data(result) {
			$("#emp_table tbody").empty();

			var emps = result.extend.pageInfo.list;

			$.each(emps, function(index, item) {

				var empIdTd = $("<td></td>").append(item.empId).attr("class",
						"text-center");
				var empNameTd = $("<td></td>").append(item.empName).attr(
						"class", "text-center");
				var empEmailTd = $("<td></td>").append(item.empEmail).attr(
						"class", "text-center");
				var empSexTd = $("<td></td>").append(item.empSex).attr("class",
						"text-center");
				var empDepTd = $("<td></td>").append(item.depName).attr(
						"class", "text-center");
				var empEdit = $("<button></button>").append("编辑").attr("class",
						"btn btn-info btn-sm");
				var empDel = $("<button></button>").append("删除").attr("class",
						"btn btn-danger btn-sm");
				var empButTd = $("<td></td>").append(empEdit).append("&nbsp;")
						.append(empDel).attr("class", "text-center");

				$("<tr></tr>").append(empIdTd).append(empNameTd).append(
						empEmailTd).append(empSexTd).append(empDepTd).append(
						empButTd).appendTo("#emp_table tbody");

			})
		}
	</script>

</body>
</html>