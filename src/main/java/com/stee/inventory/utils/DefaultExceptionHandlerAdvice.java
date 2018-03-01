package com.stee.inventory.utils;

import com.stee.inventory.Exception.ServiceException;
import com.stee.inventory.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * handle gloable exception
 */
@RestControllerAdvice
public class DefaultExceptionHandlerAdvice {

    @ExceptionHandler(ServiceException.class)
    public Result handleException(ServiceException e){

        return Result.fail(e.getMessage());
    }
}

