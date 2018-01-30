package com.stee.inventory.aop;

import com.stee.inventory.utils.LoggerUtils;
import com.stee.sel.constant.LoggerLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_CPM
 * File Name    : LoggerAdvice.java
 * Author       : Jerry
 * Created      : 2016年10月31日 上午11:35:01
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
//@Component
@Aspect
public class LoggerAdvice {

	static LoggerUtils logger = new LoggerUtils();

	@Pointcut("within(com.stee.inventory.service..*)")
	public void servicePointCut() {
	}

	@Around("servicePointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		logger.doLog(LoggerLevel.INFO, "Invoke --> Class: " + pjp.getTarget().getClass().getSimpleName()
				+ " -- Method: " + pjp.getSignature().getName() + " -- Args: " + Arrays.toString(pjp.getArgs()));
		long timeMills = System.currentTimeMillis();
		Object proceed = pjp.proceed();
		logger.doLog(LoggerLevel.INFO,
				"Finish --> Return: " + proceed + " -- Cost: " + (System.currentTimeMillis() - timeMills));
		return proceed;
	}

	@AfterThrowing(pointcut = "servicePointCut()", throwing = "e")
	public void afterThrow(Exception e) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(bos));
		logger.doLog(LoggerLevel.ERROR, "Exception --> " + bos.toString());
	}

}
