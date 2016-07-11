package com.exam.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.entity.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器");
		// TODO Auto-generated method stub
		
		//String requestUri = request.getRequestURI();
		//String contextPath = request.getContextPath();
		//String url = requestUri.substring(contextPath.length());
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null)
		{
			request.getRequestDispatcher("/login").forward(request,response);
			//System.out.println("拦截");
			return false;
		}
		else
		{
			//System.out.println("没有拦截");
			return true;
		}
		
	}
	
}
