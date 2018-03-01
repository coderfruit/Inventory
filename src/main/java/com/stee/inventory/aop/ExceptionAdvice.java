package com.stee.inventory.aop;

import com.stee.inventory.Exception.ServiceException;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAdvice {
    private static Logger logger= LoggerFactory.getLogger(ExceptionAdvice.class);

    @Pointcut("execution(* com.stee.inventory.service..*.*(..))")
    public void servicePointCut() {
    }

    @AfterThrowing(value = "servicePointCut()",throwing = "e")
    public void afterThrowing(JoinPoint jp,Exception e){
        String methodName = jp.getSignature().getName();
        e.printStackTrace();
        logger.error("class-----> {}  method-----> {}  excute failed,error message: {}",jp.getTarget(),methodName,e.getMessage());
    }


}
