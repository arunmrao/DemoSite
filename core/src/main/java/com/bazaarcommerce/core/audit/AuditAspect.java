package com.bazaarcommerce.core.audit;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * logs JSON version of objects
 * 
 * @author ar366m
 *
 */
public class AuditAspect {
	
	public Object aroundExecution(ProceedingJoinPoint jp) throws Exception {
		
		Method method = ((MethodSignature)jp.getSignature()).getMethod();
		method.getReturnType();
		
		// get Spring Session if any
//		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("*****Before method: " + jp.getSignature().getName()
				+ ". Class: " + jp.getTarget().getClass().getSimpleName());
//jp.getArgs()
		try {
			// Proceed with method invocation
			Object result = jp.proceed();

			System.out.println("*****Returning: " + result);
			return result;
		} catch (Throwable e) {
			// Log error
			System.out.println("*****Error: " + e.getMessage());
			// Throw exception to the caller
			throw new Exception("*****Error", e);
		}
	}

}
