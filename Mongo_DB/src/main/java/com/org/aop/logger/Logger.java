package com.org.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	
	@Before(value = "execution(* com.org..*(..))")
	public void MethodIn(JoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("Method In [" + start + "]" + joinPoint.getSignature());
	}
	
	@After(value = "execution(* com.org..*(..))", argNames="joinPoint")
	public void MethodOut(JoinPoint joinPoint) {
		long end = System.currentTimeMillis();
		System.out.println("Method Out [" + end + "]" +  joinPoint.getSignature());
	}
	
	@AfterThrowing(value = "execution(* com.org..*(..))", throwing="ex")
	public void LoggException(Exception ex) {
		System.out.println(ex.getMessage());
		System.out.println(ex.getCause());
	}
}
