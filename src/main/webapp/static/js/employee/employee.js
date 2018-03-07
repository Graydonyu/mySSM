//总记录数
var toatlRecord;
// 当前页码
var currentPage;
// 员工id
var empId;

$(function() {
	to_page(1);
	
	//点击搜索
	$("#search").on("click",function(){
		to_page(1);
	})

	// 保存员工信息
	$("#saveEmp").on("click", function() {	
		$("#addEmpForm").submit();
	})

	$("#addEmpForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Employee/addEmp",
				data : $("#addEmpModal form").serialize(),
				type : "post",
				success : function(result) {
					if (result.code == 0) {
						// 关闭模态框
						$("#addEmpModal").modal('toggle');

						// 跳转到最后一页
						// page只要大于最后一页就会自动查询最后一页
						to_page(toatlRecord);
					};
				}
			})
		},
		rules : {
			empName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Employee/validEmpName",
					type : "get"
				}
			},
			empEmail:{
				required : true,
			}
		},
		messages : {
			empName : {
				required : "请输入员工名",
				remote : "员工名已存在"
			},
			empEmail:{
				required : "请输入邮箱",
				email : "邮箱格式不正确"
			}
		}
	});

	// 设置点击全选与全不选
	$("#check_all").on("click", function() {
		$(".check_item").prop("checked", $(this).prop("checked"));
	})
	$("#emp_tbody").on("click", ".check_item", function() {
		var flag = $(".check_item:checked").length == $(".check_item").length;
		$("#check_all").prop("checked", flag);

	})

	// 点击打开更新员工模态框
	$("#emp_tbody").on("click",".edit_btn",function() {
		
		$("#updateEmpModal form")[0].reset();

		empId = $(this).parents("tr").attr("emp_id");

		// 获取部门列表				
		var getDeps = $.ajax({
			url : unit.rootUrl + "/Dep/deps",
			type : "get",
			success : function(result){
						
			}
		})

		// 获取到该员工数据并显示到模态框上
		var getEmpData = $.ajax({
			url : unit.rootUrl + "/Employee/getEmp/" + empId,
			type : "get",
			success : function(result){
						
			}
		})
				
		$.when(getDeps,getEmpData).done(function(result1,result2){
			$("#updateDepOptions").empty();
					
			var deps = result1[0].extend.deps;
					
			$.each(deps, function(index, item) {
				var option = $("<option></option>").append(item.depName).prop(
						"value", item.depId);
				option.appendTo("#updateDepOptions");
			})
					
			var emp = result2[0].extend.emp;
				if (result2[0].code == 0) {
					$("#updateName").val(emp.empName);
					$("#updateEmail").val(emp.empEmail);
					$("#updateEmpModal input[name = empSex]").val([ emp.empSex ]);
					$("#updateDepOptions").val(emp.depId);
				}
		});

		$("#updateEmpModal").modal({
			backdrop : false
		});
	})

	// 更新员工信息
	$("#updateEmp").on("click", function() {

		$("#updateEmpForm").submit();

	})
	
	$("#updateEmpForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Employee/updateEmp/" + empId,
				data : $("#updateEmpModal form").serialize(),
				type : "put",
				success : function(result) {
					if (result.code == 0) { 
						// 关闭模态框
						$("#updateEmpModal").modal('toggle'); 

						//刷新当前页
						to_page(currentPage);
					}
				}
			})
		},
		rules : {
			empName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Employee/validEmpName",
					type : "get",
					data : {
						empId : function(){
							return empId;
						}
					}
				}
			},
			empEmail:{
				required : true,
			}
		},
		messages : {
			empName : {
				required : "请输入员工名",
				remote : "员工名已存在"
			},
			empEmail:{
				required : "请输入邮箱",
				email : "邮箱格式不正确"
			}
		}
	});

	// 点击打开新增员工模态框
	$("#addEmp").on("click", function() {
		
		$("#addEmpModal form")[0].reset();

		getDepOptions("#depOptions");

		$("#addEmpModal").modal({
			backdrop : false
		});
	})
	
	//点击删除员工
	$("#emp_tbody").on("click",".delete_btn",function(){
		
		var empName = $(this).parents("tr").find("td:eq(2)").text();
		
		if(confirm("是否删除" + empName + "？")){
			var empIds = $(this).parents("tr").attr("emp_id");
			
			$.ajax({
				url	: unit.rootUrl + "/Employee/deleteEmp/"+empIds,
				type : "delete",
				success : function(result){
					if(result.code == 0){
						alert(result.msg);
						
						//刷新当前页
						to_page(currentPage);
					}else{
						alert(result.msg);
					}
				}
			})
		}
		
	})
	
	//批量删除
	$("#deleEmp").on("click",function(){
		var empNames = "";
		
		$.each($(".check_item:checked"),function(){
			empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
		})
		
		empNames = empNames.substring(0,empNames.length-1);
		
		if(confirm("确定要删除【"+empNames+"】吗？")){
			
			var empIds = "";
			
			$.each($(".check_item:checked"),function(){
				empIds += $(this).parents("tr").attr("emp_id")+"-";
			})
			
			empIds = empIds.substring(0,empIds.length-1);
			
			$.ajax({
				url	:	unit.rootUrl+"/Employee/deleteEmp/"+empIds,
				type : "delete",
				success : function(result){
					if(result.code == 0){
						alert(result.msg);
						
						//刷新当前页
						to_page(currentPage);
					}else{
						alert(result.msg);
					}
				}
			})
		}
	})
})

function to_page(page) {
	
	var search = $("#search_val").val().trim();
	var data = "page="+page;
	
	if(search != ""){
		data = data+"&search="+search;
	}
	
	$.ajax({
		url : unit.rootUrl + "/Employee/emps",
		data : data,
		type : "get",
		success : function(result) {

			build_table_data(result);

			build_page_msg(result);

			build_page_nav(result);
		}
	})
}

// 获取所有部门并构建模态框部门选项
function getDepOptions(ele) {

	var getDeps = $.ajax({
		url : unit.rootUrl + "/Dep/deps",
		type : "get",
		success : function(result) {

			$(ele).empty();
			
			$.each(result.extend.deps, function(index, item) {
				var option = $("<option></option>").append(item.depName).prop(
						"value", item.depId);
				option.appendTo(ele);
			})

		}
	})
}

// 构建分页条
function build_page_nav(result) {
	$("#page_nav").empty();

	var pageInfo = result.extend.pageInfo;

	var nav = $("<nav></nav>");
	var ul = $("<ul></ul>").attr("class", "pagination");

	var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
	var lastPageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));

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

// 构建分页信息
function build_page_msg(result) {
	$("#page_msg").empty();

	var pageMsg = result.extend.pageInfo;

	$("#page_msg").append(
			"当前第" + pageMsg.pageNum + "页，总" + pageMsg.pages + "页，总"
					+ pageMsg.total + "条记录");

	toatlRecord = pageMsg.total;
	currentPage = pageMsg.pageNum;

}

// 解析数据填充表格
function build_table_data(result) {
	$("#emp_table tbody").empty();

	var emps = result.extend.pageInfo.list;

	$.each(emps, function(index, item) {

		var checkboxTd = $(
				"<td><input type='checkbox' class='check_item'/></td>").attr(
				"class", "text-center");

		var empIdTd = $("<td></td>").append(item.empId).attr("class",
				"text-center");
		var empNameTd = $("<td></td>").append(item.empName).attr("class",
				"text-center");
		var empEmailTd = $("<td></td>").append(item.empEmail).attr("class",
				"text-center");
		var empSexTd = $("<td></td>").append(item.empSex).attr("class",
				"text-center");
		var empDepTd = $("<td></td>").append(item.depName).attr("class",
				"text-center");
		var empEdit = $("<button></button>").append("编辑").attr("class",
				"btn btn-info btn-sm edit_btn");
		var empDel = $("<button></button>").append("删除").attr("class",
				"btn btn-danger btn-sm delete_btn");
		var empButTd = $("<td></td>").append(empEdit).append("&nbsp;").append(
				empDel).attr("class", "text-center");

		$("<tr></tr>").append(checkboxTd).append(empIdTd).append(empNameTd)
				.append(empEmailTd).append(empSexTd).append(empDepTd).append(
						empButTd).attr("emp_id", item.empId).appendTo(
						"#emp_table tbody");

	})
}