package com.stee.inventory.entity;

import com.stee.inventory.enums.ResponseCode;

import javax.xml.ws.Response;
import java.io.Serializable;

/**
 * response to front end
 * @Authon luffy
 */
public class Result<T> implements Serializable{
    /**
     * status code
     */
    private String statusCode;
    /**
     * some message
     */
    private String message;
    /**
     * data
     */
    private T data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    /**
     * if operation succeed, use this
     * @param data
     * @return
     */
    public  Result success(T data){
        Result<T> result=new Result();
        result.setStatusCode(ResponseCode.SUCCESS.getCode());
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * overload success method
     * @return
     */
    public static Result success(){
        Result result=new Result();
        result.setStatusCode(ResponseCode.SUCCESS.getCode());
        result.setMessage("success");
        return result;
    }

    public static Result warn(String message){
        Result result=new Result();
        result.setStatusCode(ResponseCode.ERROR_PARAM.getCode());
        result.setMessage(message);
        return result;
    }
    /**
     * if operation failed, use this
     * @return
     */
    public static Result fail(){
        Result result=new Result();
        result.setStatusCode(ResponseCode.FAILED.getCode());
        result.setMessage("fail");
        return result;
    }

    public static Result fail(String message){
        Result result=new Result();
        result.setStatusCode(ResponseCode.FAILED.getCode());
        result.setMessage(message);
        return result;
    }
}
