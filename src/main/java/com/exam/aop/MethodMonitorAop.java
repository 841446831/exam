package com.exam.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.OpInc;

import ch.qos.logback.classic.Logger;

@Aspect
public class MethodMonitorAop {
	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.exam.controller.*.*(..))")
	 public void allMethods() {}  
	
	
	@Around(value="allMethods()")
	 public Object beforeAdvice(ProceedingJoinPoint point) throws Throwable {
		Object target = point.getTarget();
		logger.info(target.getClass() +" " +point.getSignature().getName()+"方法");
		logger.info("方法参数为:"+Arrays.toString(point.getArgs()));
		Object returnValue = point.proceed();
		logger.info("返回值为:"+returnValue.toString());
		return  returnValue;
	}
}
