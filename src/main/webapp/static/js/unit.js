unit = (function() {

	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));

	return {
		rootUrl : window.location.protocol + '//' + window.location.host + '/'
		+ webName,
	};
}());