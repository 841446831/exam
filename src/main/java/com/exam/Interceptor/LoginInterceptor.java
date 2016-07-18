package com.exam.Interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器1...");
    	//final String[] ignore_uri = {"login","types"};
    	List<String> ignore_uri = new ArrayList<String>();
    	ignore_uri.add("/login");
    	ignore_uri.add("/types");
    	ignore_uri.add("/selectNowExam");
    	
    	String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String requestUrl = request.getRequestURL().toString();
    	System.out.println(url);
    	System.out.println(requestUrl);
		
    	User user = (User) request.getSession().getAttribute("user");
    	
//    	if(user == null)
//    	{
//    		return true;
//    	}
//    	else
//    	{
//    		
//    		return false;
//    	}
    	
    	return true;
    	
    	
    	
//    	if(!ignore_uri.contains(url))
//    	{
//    		if(user != null)
//    		{
//    			return true;
//    		}
//    		else
//    		{
//    			return false;
//    		}
//    	}
//    	else
//    	{
//    		
//    		return true;
//    	}
    	
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler,
    		ModelAndView modelAndView) throws Exception {
    	
    	System.out.println("进入postHandle1....");
    	//modelAndView.setViewName("");
    }
}
