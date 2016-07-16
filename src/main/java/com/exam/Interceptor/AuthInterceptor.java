package com.exam.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.entity.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器...");
		// TODO Auto-generated method stub
		
		final String[] ignoreUri = {"/login"};
		boolean flag = false;
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String requestUrl = request.getRequestURL().toString();
		
		for(String s : ignoreUri)
		{
			if(s.equals(url))
			{
				flag = true;
				System.out.println(flag);
				break;
			}
			
		}
		
		System.out.println(requestUri);
		System.out.println(contextPath);
		System.out.println(url);
		System.out.println(requestUrl);
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user != null)
		{
			//request.getRequestDispatcher("tags.html").forward(request,response);
			//System.out.println("拦截");
			//return false;
			flag = true;
		}
	
		return true;
		
	}
	
}


