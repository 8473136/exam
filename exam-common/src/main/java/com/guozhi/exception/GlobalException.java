package com.guozhi.exception;

import com.guozhi.core.ResultStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description 全局业务异常声明
 * @author LiuChangLan
 * @since 2020/7/13 10:22
 */
@Getter
@AllArgsConstructor
public enum GlobalException implements ResultStatusEnum {
    NO_LOGIN(1000,"未登录"),
    ACCOUNT_OR_PASSWORD_INCORRECT(1001,"用户名不存在、或者密码不正确!"),
    ACCOUNT_DISABLE(1002,"用户已禁用"),
    ACCOUNT_NOT_EXISTS(1003,"用户名不存在"),






    IS_NOT_IN_EXAM_TIME(2000,"考试已经结束");
    private Integer code;
    private String msg;
}
