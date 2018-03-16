$().ready(function() {
	$("#login_form").validate({
		rules : {
			username : "required",
			password : {
				required : true,
				minlength : 5
			},
		},
		messages : {
			username : "请输入姓名",
			password : {
				required : "请输入密码",
				minlength : jQuery.format("密码不能小于{0}个字 符")
			},
		}
	});

	$("#loginButton").on("click", function() {
		$.ajax({
			url : unit.rootUrl + "/Manager/login",
			data : $("#login_form").serialize(),
			type : "get",
			success : function(result) {
				if(result.code == 0){
					window.location.href = unit.rootUrl+"/employee.jsp";
				}else{
					$('.alert').html('登录失败，请检查用户名和密码！').addClass('alert-warning').show().delay(1500).fadeOut();
				}
			}
		})
	})
});
$(function() {

});