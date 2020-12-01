package com.guozhi.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuchangLan
 * @date 2020/6/27 22:02
 */
@Getter
@AllArgsConstructor
public enum ResultStatusCode implements ResultStatusEnum {
    /** 正常返回*/
    SUCCESS(200, "OK"),
    SERVER_ERROR(500, "服务器内部错误"),
    METHOD_TYPE_EXCEPTION(405,"请求方式错误"),
    PAPER_COMMIT_SUCCESS(200,"交卷成功"),
    PAPER_COMMIT_ERROR(500,"交卷失败"),
    PAPER_SAVE_SUCCESS(200,"保存成功"),
    PAPER_SAVE_ERROR(200,"保存失败");

    private Integer code;
    private String msg;
}
