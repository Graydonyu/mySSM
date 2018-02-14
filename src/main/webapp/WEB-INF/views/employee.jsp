<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8 text-right">
				<button type="button" class="btn btn-primary">新增</button>
				<button type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped table-hover">
					<tr>
						<th class="text-center">#</th>
						<th class="text-center">empName</th>
						<th class="text-center">email</th>
						<th class="text-center">gender</th>
						<th class="text-center">deptName</th>
						<th class="text-center">操作</th>
					</tr>
					<c:forEach items="${ pageInfo.list }" var="emp">
						<tr>
							<td class="text-center">${ emp.empId}</td>
							<td class="text-center">${ emp.empName}</td>
							<td class="text-center">${ emp.empEmail}</td>
							<td class="text-center">${ emp.empSex}</td>
							<td class="text-center">${ emp.depId}</td>
							<td class="text-center">
								<button type="button" class="btn btn-info btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
								</button>
								<button type="button" class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">当前第${ pageInfo.pageNum }页，总${ pageInfo.pages }页，总${ pageInfo.total }条记录
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<c:if test="${ !pageInfo.isFirstPage }">
						<li><a href="${APP_PATH }/Employee/allList?page=1">首页</a></li>
					</c:if>
					<c:if test="${ pageInfo.hasPreviousPage }">
						<li><a
							href="${APP_PATH }/Employee/allList?page=${ pageInfo.pageNum-1 }"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach items="${ pageInfo.navigatepageNums }" var="pageNums">
						<c:if test="${ pageNums == pageInfo.pageNum }">
							<li class="active"><a href="#">${ pageNums }</a></li>
						</c:if>
						<c:if test="${ pageNums != pageInfo.pageNum }">
							<li><a
								href="${APP_PATH }/Employee/allList?page=${ pageNums }">${ pageNums }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${ pageInfo.hasNextPage}">
						<li><a
							href="${APP_PATH }/Employee/allList?page=${ pageInfo.pageNum+1 }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>

					</c:if>
					<c:if test="${ !pageInfo.isLastPage }">
						<li><a
							href="${APP_PATH }/Employee/allList?page=${ pageInfo.pages }">末页</a></li>
					</c:if>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>