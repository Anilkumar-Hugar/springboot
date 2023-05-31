package com.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class PersonAop {
	Logger logger = LoggerFactory.getLogger(PersonAop.class);

	@Around(value = "pointCut()")
	public Object aoplog(ProceedingJoinPoint joinPoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String signature = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().toString();
		logger.info("method invoked from " + className + " with method name " + signature);
		Object object = joinPoint.proceed();
		logger.info("Response from the DB: {}",mapper.writeValueAsString(object));
		return object;
	}

	@Pointcut(value = "execution(* com.aop.service.*.*(..))")
	public void pointCut() {

	}
}
