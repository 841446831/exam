//package com.exam.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//
//@Order(1)
//@Aspect
//public class LoginAspect {
//    
//	@Pointcut("execution(* com.exam.controller.*.*(..))")
//	public void loginMethod(){}
//	
//	
//	@Around(value="loginMethod()")
//	public Object aroundLogin(ProceedingJoinPoint point) throws Throwable {
//		System.out.println("before login");
//		Object target = point.getTarget();
//		Object returnValue = point.proceed();
//		return  returnValue;
//	}
//	
//}
