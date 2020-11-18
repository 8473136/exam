package com.guozhi.rvo;

import lombok.Data;

/**
 * 日志返回rvo
 * @author LiuchangLan
 * @date 2020/7/24 10:21
 */
@Data
public class LogRVO {

    // 日志类型
    private String logType;

    // 方法名
    private String execMethod;

    // 参数
    private String params;

    // 操作时间
    private String execTime;

    // 操作模块
    private String module;

    // 操作业务
    private String business;

    // 操作ip
    private String execIp;

    // 具体内容
    private String logContent;

    // 用户名
    private String userName;

}
