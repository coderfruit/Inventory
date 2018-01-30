package com.stee.inventory.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stee.sel.atm.LogInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
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
 * Project Name : ocm
 * File Name    : LogInterceptor.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月28日 下午3:43:29
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public class LogInterceptor {
	@Autowired@Qualifier("restTemplate")
	private RestTemplate restTemplate;
//	private static ILogger logger = null;
	public static Logger log = LoggerFactory.getLogger(LogInterceptor.class);
	/**
	 * 加载时就加载日志 工具
	 */
//	public LogInterceptor(){
//		try {
//			String logAddress = PropUtils.getContextProperty("logAddress").toString();
//			String logPort = PropUtils.getContextProperty("logPort").toString();
//			logger= DefaultLoggerFactory.buildUdpLogger(logAddress,logPort);
//		} catch (NullParametersException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 执行前方法
	 * @param jp
	 */
	public void doBefore(JoinPoint jp){
		String className = jp.getTarget().getClass().getName();
		String methodName= jp.getSignature().getName();
		StringBuffer logStr = new StringBuffer(ConfigUtil.getNowTimeString()).
				append(" ").append(className.substring(className.lastIndexOf(".") + 1))
				.append( " class's < ").append(methodName).append(" > method begin execute  ---> ");
		if(jp.getArgs().length>0){
			logStr.append("parmeters  ( ");
			for (Object obj : jp.getArgs()) {
				if(obj!=null){
					String type = obj.getClass().getName();
					type = type.substring(type.lastIndexOf(".") + 1);
					String arg = type + ":" + obj + " ";
					logStr.append(arg);
				}
			}
			logStr = logStr.append(" )");
		}
//		logger.info(logStr.toString());
		log.info(logStr.toString());
	}
	
	
	/**
	 * 抛出异常时执行方法
	 * @param jp
	 * @param throwable
	 */
	public void throwException(JoinPoint jp, Throwable throwable) {
		String targetClass = jp.getTarget().getClass().getName();
		String method = jp.getSignature().getName();
		String message = throwable.getMessage();
//		logger.info(targetClass + " class's" + method + "  method throw Exception：<" + message + ">");
		log.info(targetClass + " class's" + method + "  method throw Exception：<" + message + ">");
	}
	
	
	public void doAfter(JoinPoint jp){
		String className = jp.getTarget().getClass().getName();
		String methodName= jp.getSignature().getName();
		StringBuffer logStr = new StringBuffer(ConfigUtil.getNowTimeString())
			.append(" ").append(className.substring(className.lastIndexOf(".") + 1))
				.append( " class's < ").append(methodName).append(" > method execute end  ---> ");
//		logger.info(logStr.toString());
		log.info(logStr.toString());
	}
	
	/**
	 * 执行方法返回 后 执行该方法 对标记有 @Log标记的进行入库
	 * @param jp
	 */
	public void saveLog(JoinPoint jp){
		Log log = giveController(jp);
		if(log == null){
			return;
		}
		saveLogToDb(log.moduleName(),jp.getSignature().getName(),log.desc(), jp.getArgs());
	}
	
	/**
	 * 入库方法
	 * @param moduleName
	 * @param methodName
	 * @param desc
	 * @param params
	 */
	private void saveLogToDb(String moduleName,String methodName,String desc,Object[] params){
		Gson gson=new GsonBuilder().setDateFormat("yy-MM-dd HH:mm").create();
		StringBuffer param = new StringBuffer();
		try{
			byte[] buff=gson.toJson(params).getBytes(ConfigUtil.ENCODING_NAME);
			if(buff.length<=1000){
				param.append(new String(buff,ConfigUtil.ENCODING_NAME));  //解决出现的日志入库时，传递中文时乱码问题
			}else{
				buff=Arrays.copyOf(buff, 1000);
				String p=new String(buff,ConfigUtil.ENCODING_NAME);
				if(!String.valueOf(p.charAt(p.length()-1)).matches("[a-zA-Z0-9]")){
					p=p.substring(0,p.length()-1);
				}
				param.append(p);
			}
		}catch(Exception e){
			
		}
		LogInfo log = new LogInfo();
		log.setMethodname(methodName);
		log.setDescribeinfo(desc);
		log.setParam(param.toString());
		log.setContent(moduleName);
		restTemplate.postForEntity(ConfigUtil.ATM_SAVELOG_ADDRESS, log, null);
	}
	
	
	/**
	 * 判断是否有 @Log 注解，如果有，返回该注解，如果没有，返回null
	 * @param joinPoint
	 * @return
	 */
	private Log giveController(JoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        return method == null ? null : (Log)method.getAnnotation(com.stee.inventory.utils.Log.class);
	}
	
}
