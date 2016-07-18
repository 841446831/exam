package com.exam.Interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.entity.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入拦截器2...");
		// TODO Auto-generated method stub
		
		//final String[] ignoreUri = {"/manage","/user"};
		//boolean flag = false;
		
		List<String> ignoreUri = new ArrayList<String>();
		//ignoreUri.add("/manage");
		ignoreUri.add("/users");
		ignoreUri.add("/user");
		ignoreUri.add("/questions");
		ignoreUri.add("/exams");
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String requestUrl = request.getRequestURL().toString();
	
		User user = (User) request.getSession().getAttribute("user");
		
		if(ignoreUri.contains(url))
		{
			if(user.getGroup()==2 || user.getGroup() == 1)
			{
				return true;
			}
			else
			{
				//request.getRequestDispatcher("/nopower.ftl").forward(request, response);
				return false;
			}
		}
		else
		{
			return true;
		}
	
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//super.postHandle(request, response, handler, modelAndView);
		System.out.println("进入postHandle2....");
		//System.out.println(modelAndView);
//		if(modelAndView != null)
//		{
//			modelAndView.setViewName("/nopower.ftl");
//		}
	}
	
}


