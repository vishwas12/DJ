package com.dj.application.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;

import com.dj.application.exception.CustomExceptionHandler;
import com.dj.application.exception.CustomGenericException;

@Aspect
public class LoggingAspect {

	@Around("allDaoMethods() || allDaoImplMethods() || allServiceMethods() || allControllerMethods()")
	public Object loggingAllDaoMethods(ProceedingJoinPoint pj){
		Object [] args = pj.getArgs();
		Signature signature =pj.getSignature();
		String methodName = signature.getName();
		String className = signature.getDeclaringTypeName();
		Object returnValue = null;
		final Logger LOGGER = Logger.getLogger(className);
		try {
			LOGGER.info("Invoking [ " + methodName + " ] with paramaters -->" +args.toString());
			returnValue = pj.proceed();
			LOGGER.debug("Exiting [ " + methodName + " ]");
		} catch (Throwable e) {
			LOGGER.error("Caused By : ", e);
			throw new CustomGenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Exiting [ " + methodName + " ] with return value -->" + returnValue);
		return returnValue;
	}
	
	@Pointcut("within(com.dj.dao..*)")
	public void allDaoMethods(){};
	
	@Pointcut("within(com.dj.dao.impl..*)")
	public void allDaoImplMethods(){};
	
	@Pointcut("within(com.dj.service.impl..*)")
	public void allServiceMethods(){};
	
	@Pointcut("within(com.dj.controller..*)")
	public void allControllerMethods(){};
}
