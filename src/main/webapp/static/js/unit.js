//返回公共得url和方法
unit = (function() {

	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));

	//如果后台返回未登录，则跳转到登陆页面
	var isLogin = function(result){
		if(result == "noLogin"){
			$('.alert').html('登录失效，请重新登录！').addClass('alert-warning text-center').show().delay(1000).fadeOut();
			setTimeout(function(){
				window.location.href = window.location.protocol + '//' + window.location.host + '/'
				+ webName +"/login.jsp";
			},1000);
		}
	}
	
	return {
		
		rootUrl : window.location.protocol + '//' + window.location.host + '/'
		+ webName,
		isLogin : isLogin
	};
}());