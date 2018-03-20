/**   
* @Description: 拦截器，拦截所有请求验证登录状态 
* @author ygd  
* @date 2018年3月18日  
*/ 
package com.ygd.SSM2.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

	/**  
	* @Title: preHandle  
	* @Description: handler处理器执行之前
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)  
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String manName = (String) request.getSession().getAttribute("manName");
		
		//管理员未登录，拦截除登录外的所有请求
		if(StringUtils.isBlank(manName)){
			System.out.println("kong ?");
			
			request.getRequestDispatcher("/").forward(request,response);
			
			return false;
		}
		
		System.out.println("放行？");
		
		//已登录，放行
		return true;
	}

	/**  
	* @Title: postHandle  
	* @Description: handler处理器执行完成，返回modalandview之前
	* @param request
	* @param response
	* @param handler
	* @param modelAndView
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)  
	*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**  
	* @Title: afterCompletion  
	* @Description: handler处理器执行全部完成之后
	* @param request
	* @param response
	* @param handler
	* @param ex
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)  
	*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


	

}
