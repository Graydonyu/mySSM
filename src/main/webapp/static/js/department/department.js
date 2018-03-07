//总记录数
var toatlRecord;
// 当前页码
var currentPage;
// 部门id
var depId;

$(function() {
	to_page(1);

	// 保存部门信息
	$("#saveDep").on("click", function() {	
		$("#addDepForm").submit();
	})

	$("#addDepForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Dep/saveDep",
				data : $("#addDepModal form").serialize(),
				type : "post",
				success : function(result) {
					if (result.code == 0) {
						// 关闭模态框
						$("#addDepModal").modal('toggle');

						// 跳转到最后一页
						// page只要大于最后一页就会自动查询最后一页
						to_page(toatlRecord);
					};
				}
			})
		},
		rules : {
			depName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Dep/validDep",
					type : "get"
				}
			}
		},
		messages : {
			depName : {
				required : "请输入部门名",
				remote : "部门名已存在"
			}
		}
	});

	// 设置点击全选与全不选
	$("#check_all").on("click", function() {
		$(".check_item").prop("checked", $(this).prop("checked"));
	})
	$("#dep_tbody").on("click", ".check_item", function() {
		var flag = $(".check_item:checked").length == $(".check_item").length;
		$("#check_all").prop("checked", flag);

	})

	// 点击打开更新员工模态框
	$("#dep_tbody").on("click",".edit_btn",function() {
		
		$("#updateDepModal form")[0].reset();		

		depId = $(this).parents("tr").attr("dep_id");

		// 获取到部门数据并显示到模态框上
		$.ajax({
			url : unit.rootUrl + "/Dep/getDep/" + depId,
			type : "get",
			success : function(result){
				var dep = result.extend.dep;
				if (result.code == 0) {
					$("#updateName").val(dep.depName);
				}
			}
		})

		$("#updateDepModal").modal({
			backdrop : false
		});
	})

	// 更新员工信息
	$("#updateDep").on("click", function() {

		$("#updateDepForm").submit();

	})
	
	$("#updateDepForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Dep/updateDep/" + depId,
				data : $("#updateDepModal form").serialize(),
				type : "put",
				success : function(result) {
					if (result.code == 0) { 
						// 关闭模态框
						$("#updateDepModal").modal('toggle'); 

						//刷新当前页
						to_page(currentPage);
					}
				}
			})
		},
		rules : {
			depName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Dep/validDep",
					type : "get",
					data : {
						depId : function(){
							return depId;
						}
					}
				}
			}
		},
		messages : {
			depName : {
				required : "请输入部门名",
				remote : "部门名已存在"
			}
		}
	});

	// 点击打开新增员工模态框
	$("#addDep").on("click", function() {
		
		$("#addDepModal form").data('bootstrapValidator').resetForm();

		$("#addDepModal").modal({
			backdrop : false
		});
	})
	
	//点击删除部门
	$("#dep_tbody").on("click",".delete_btn",function(){
		
		var depName = $(this).parents("tr").find("td:eq(2)").text();
		
		if(confirm("是否删除" + depName + "？")){
			var depIds = $(this).parents("tr").attr("dep_id");
			
			$.ajax({
				url	: unit.rootUrl + "/Dep/deleteDep/"+depIds,
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
	$("#deleDep").on("click",function(){
		var depNames = "";
		
		$.each($(".check_item:checked"),function(){
			depNames += $(this).parents("tr").find("td:eq(2)").text()+",";
		})
		
		depNames = depNames.substring(0,depNames.length-1);
		
		if(confirm("确定要删除【"+depNames+"】吗？")){
			
			var depIds = "";
			
			$.each($(".check_item:checked"),function(){
				depIds += $(this).parents("tr").attr("dep_id")+"-";
			})
			
			depIds = depIds.substring(0,depIds.length-1);
			
			$.ajax({
				url	:	unit.rootUrl+"/Dep/deleteDep/"+depIds,
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
	$.ajax({
		url : unit.rootUrl + "/Dep/depList",
		data : "page=" + page,
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
	$("#dep_tbody").empty();
	
	var deps = result.extend.pageInfo.list;

	$.each(deps, function(index, item) {
		
		var checkboxTd = $("<td><input type='checkbox' class='check_item'/></td>").attr("class",
				"text-center");

		var depIdTd = $("<td></td>").append(item.depId).attr("class",
				"text-center");
		var depNameTd = $("<td></td>").append(item.depName).attr("class",
				"text-center");
		var empSizeTd = $("<td></td>").append("13").attr("class",
		"text-center");
		var depEdit = $("<button></button>").append("编辑").attr("class",
				"btn btn-info btn-sm edit_btn");
		var depDel = $("<button></button>").append("删除").attr("class",
				"btn btn-danger btn-sm delete_btn");
		var depButTd = $("<td></td>").append(depEdit).append("&nbsp;").append(
				depDel).attr("class", "text-center");

		$("<tr></tr>").append(checkboxTd).append(depIdTd).append(depNameTd)
				.append(empSizeTd).append(depButTd).attr("dep_id", item.depId).appendTo(
						"#dep_tbody");

	})
}