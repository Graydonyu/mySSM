//总记录数
var toatlRecord;
// 当前页码
var currentPage;
// 管理员id
var manId;

$(function() {
	to_page(1);
	
	//点击搜索
	$("#search").on("click",function(){
		to_page(1);
	})

	// 保存管理员信息
	$("#saveMan").on("click", function() {	
		$("#addManForm").submit();
	})

	$("#addManForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Manager/addMana",
				data : $("#addManModal form").serialize(),
				type : "post",
				success : function(result) {
					
					unit.isLogin(result);
					
					if (result.code == 0) {
						
						// 关闭模态框
						$("#addManModal").modal('toggle');

						// 跳转到最后一页
						// page只要大于最后一页就会自动查询最后一页
						to_page(toatlRecord);
					};
				}
			})
		},
		rules : {
			manName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Manager/validaMana",
					type : "get"
				}
			},
			manPassword : {
				 required: true
			},
			confirm_password : {
				required: true,
			    equalTo: "#addPassword1"
			}
		},
		messages : {
			manName : {
				required : "请输入管理员名",
				remote : "管理员名已存在"
			},
			manPassword : {
				 required: "请输入密码"
			},
			confirm_password : {
				required: "请再次输入密码",
			    equalTo: "密码输入不一致！"
			}
		}
	});

	// 设置点击全选与全不选
	$("#check_all").on("click", function() {
		$(".check_item").prop("checked", $(this).prop("checked"));
	})
	$("#man_tbody").on("click", ".check_item", function() {
		var flag = $(".check_item:checked").length == $(".check_item").length;
		$("#check_all").prop("checked", flag);

	})

	// 点击打开更新管理员模态框
	$("#man_tbody").on("click",".edit_btn",function() {
		
		$("#updateManModal form")[0].reset();		

		manId = $(this).parents("tr").attr("man_id");

		// 获取到管理员数据并显示到模态框上
		$.ajax({
			url : unit.rootUrl + "/Manager/getManager/" + manId,
			type : "get",
			success : function(result){
				
				unit.isLogin(result);
				
				var man = result.extend.man;
				if (result.code == 0) {
					$("#updateName").val(man.manName);
				}
			}
		})

		$("#updateManModal").modal({
			backdrop : false
		});
	})

	// 更新员工信息
	$("#updateMan").on("click", function() {
		$("#updateManForm").submit();

	})
	
	$("#updateManForm").validate({
		submitHandler : function(form){
			$.ajax({
				url : unit.rootUrl + "/Manager/upMana/" + manId,
				data : $("#updateManModal form").serialize(),
				type : "put",
				success : function(result) {
					
					unit.isLogin(result);
					
					if (result.code == 0) { 
						// 关闭模态框
						$("#updateManModal").modal('toggle'); 

						//刷新当前页
						to_page(currentPage);
					}
				}
			})
		},
		rules : {
			manName : {
				required : true,
				remote : {
					url : unit.rootUrl+"/Manager/validaMana",
					type : "get",
					data : {
						manId : function(){
							return manId;
						}
					}
				}
			},
			manPassword : {
				 required: true
			},
			confirm_password : {
				required: true,
			    equalTo: "#updatePassword1"
			}
		},
		messages : {
			manName : {
				required : "请输入管理员名",
				remote : "管理员名已存在"
			},
			manPassword : {
				 required: "请输入密码"
			},
			confirm_password : {
				required: "请再次输入密码",
			    equalTo: "密码输入不一致！"
			}
		}
	});

	// 点击打开新增管理员模态框
	$("#addMan").on("click", function() {
		
		$("#addManModal form")[0].reset();

		$("#addManModal").modal({
			backdrop : false
		});
	})
	
	//点击删除管理员
	$("#man_tbody").on("click",".delete_btn",function(){
		
		var manName = $(this).parents("tr").find("td:eq(2)").text();
		
		if(confirm("是否删除" + manName + "管理员？")){
			var manIds = $(this).parents("tr").attr("man_id");
			
			$.ajax({
				url	: unit.rootUrl + "/Manager/deleteManas/"+manIds,
				type : "delete",
				success : function(result){
					
					unit.isLogin(result);
					
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
	$("#deleMan").on("click",function(){
		
		var count = $(".check_item:checked").length;
		
		var manNames = "";
		
		//判断选中个数
		if(count>0){
			
			$.each($(".check_item:checked"),function(){
				manNames += $(this).parents("tr").find("td:eq(2)").text()+",";
			})
			
			manNames = manNames.substring(0,manNames.length-1);
			
			if(confirm("确定要删除【"+manNames+"】管理员吗？")){
				
				var manIds = "";
				
				$.each($(".check_item:checked"),function(){
					manIds += $(this).parents("tr").attr("man_id")+"-";
				})
				
				manIds = manIds.substring(0,manIds.length-1);
				
				$.ajax({
					url	:	unit.rootUrl+"/Manager/deleteManas/"+manIds,
					type : "delete",
					success : function(result){
						
						unit.isLogin(result);
						
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
			
		}else{
			
			alert("请删除要勾选的条目");
			
		}
				
	})
})

function to_page(page) {
	
	var search = $("#search_val").val().trim();
	
	var data = "page="+page;
	
	if(search != ""){
		data += "&search=" + search;
	}
	
	$.ajax({
		url : unit.rootUrl + "/Manager/list",
		data : data,
		type : "get",
		success : function(result) {
			
			unit.isLogin(result);

			if(result.code == 0){
				build_table_data(result);

				build_page_msg(result);

				build_page_nav(result);
			}
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
	$("#man_tbody").empty();
	
	var mans = result.extend.pageInfo.list;

	$.each(mans, function(index, item) {
		
		var checkboxTd = $("<td><input type='checkbox' class='check_item'/></td>").attr("class",
				"text-center");

		var manIdTd = $("<td></td>").append(item.manId).attr("class",
				"text-center");
		var manNameTd = $("<td></td>").append(item.manName).attr("class",
				"text-center");
		var manLevelTd = $("<td></td>").append(item.manLevel).attr("class",
		"text-center");
		var manEdit = $("<button></button>").append("编辑").attr("class",
				"btn btn-info btn-sm edit_btn");
		var manDel = $("<button></button>").append("删除").attr("class",
				"btn btn-danger btn-sm delete_btn");
		var manButTd = $("<td></td>").append(manEdit).append("&nbsp;").append(
				manDel).attr("class", "text-center");

		$("<tr></tr>").append(checkboxTd).append(manIdTd).append(manNameTd)
				.append(manLevelTd).append(manButTd).attr("man_id", item.manId).appendTo(
						"#man_tbody");

	})
}