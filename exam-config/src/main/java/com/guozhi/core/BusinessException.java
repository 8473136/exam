package com.guozhi.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 业务异常
 * @author LiuchangLan
 * @date 2020/6/27 22:23
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class BusinessException extends Exception{
    private Integer errorCode;
    private String errorMsg;

    public BusinessException(ResultStatusEnum resultStatusEnum){
        super("业务异常 - " + resultStatusEnum.getMsg());
        this.errorCode = resultStatusEnum.getCode();
        this.errorMsg =resultStatusEnum.getMsg();
    }
}
