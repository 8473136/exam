package com.guozhi.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理异常
 * @author LiuchangLan
 * @date 2020/6/27 22:19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        // 控制台打印错误
        log.error(e.getMessage(),e);
        Result result = new Result();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            result.setCode(businessException.getErrorCode());
            result.setMsg(businessException.getErrorMsg());
        }else if (e instanceof HttpRequestMethodNotSupportedException){
            result.setCode(ResultStatusCode.METHOD_TYPE_EXCEPTION.getCode());
            result.setMsg(ResultStatusCode.METHOD_TYPE_EXCEPTION.getMsg());
        }
        else {
            result.setCode(ResultStatusCode.SERVER_ERROR.getCode());
            result.setMsg(ResultStatusCode.SERVER_ERROR.getMsg());
        }
        return result;
    }
}
