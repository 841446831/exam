//package com.exam.Interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.exam.entity.User;
//
//public class AuthInterceptor extends HandlerInterceptorAdapter {
//    
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("进入拦截器");
//		// TODO Auto-generated method stub
//		
//		//String requestUri = request.getRequestURI();
//		//String contextPath = request.getContextPath();
//		//String url = requestUri.substring(contextPath.length());
////		
////		User user = (User) request.getSession().getAttribute("user");
////		
////		if(user == null)
////		{
////			//request.getRequestDispatcher("login").forward(request,response);
////			//System.out.println("拦截");
////			return false;
////		}
////		else
////		{
//			//System.out.println("没有拦截");
//			return true;
////		}
//		
//	}
//	
//}

//	<!--配置拦截器, 多个拦截器,顺序执行 -->  
//    <mvc:interceptors>    
//        <mvc:interceptor>    
//            <!-- 匹配的是url路径， 如果不配置或/**,将拦截所有的Controller -->  
//            <mvc:mapping path="/**" />  
//            <mvc:exclude-mapping path="/login"/>  
//            <bean class="com.exam.Interceptor.AuthInterceptor"></bean>    
//        </mvc:interceptor>  
//        <!-- 当设置多个拦截器时，先按顺序调用preHandle方法，然后逆序调用每个拦截器的postHandle和afterCompletion方法 -->  
//    </mvc:interceptors>  
