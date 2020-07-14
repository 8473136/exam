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
    NO_LOGIN(1000,"未登录");
    private Integer code;
    private String msg;
}
