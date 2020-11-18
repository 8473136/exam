package com.guozhi.core;

import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/6/27 22:00
 */
@Data
public class Result {
    /**
     * 返回的代码，200表示成功，其他表示失败
     */
    private Integer code;
    /**
     * 成功或失败时返回的错误信息
     */
    private String msg;
    /**
     * 成功时返回的数据信息
     */
    private Object data;
}
