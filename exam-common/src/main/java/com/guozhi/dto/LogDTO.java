package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 日志dto
 * @author LiuchangLan
 * @date 2020/7/23 15:13
 */
@Data
@Table(name = "tab_sys_log")
public class LogDTO extends BaseDTO{
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

    // 用户id
    private Integer userId;
}
