package com.guozhi.common;

/**
 * 全局常量
 * @author LiuchangLan
 * @date 2020/7/14 15:00
 */
public class DataGlobalVariable {

    /** 用户激活状态码*/
    public static final Integer USER_STATUS_DISABLE = 1;

    /** 载体在服务间传递时，header 中使用的名称*/
    public static final String HEADER_ACCCESS_TOKEN = "accessToken";

    /** accessToken有效期*/
    public static final long ACCESS_TOKEN_EFFECTIVE = 2 * 60 * 1000L;

    /** 单选题 字典表 id*/
    public static final Integer SINGLE_QUESTION_DICT_ID = 8;

    /** 多选题 字典表 id*/
    public static final Integer MULTIPLE_QUESTION_DICT_ID = 9;

    /** 判断题 字典表 id*/
    public static final Integer JUDGE_QUESTION_DICT_ID = 10;

    /** 填空题 字典表 id*/
    public static final Integer FILL_QUESTION_DICT_ID = 11;

    /** 操作日志 字典表 id*/
    public static final Integer EXEC_LOG_DICT_ID = 18;

    /** 错误日志 字典表 id*/
    public static final Integer EXCEPTION_LOG_DICT_ID = 19;

    /** 登录授权的路径*/
    public static final String AUTH_CLASS_PATH = "com.guozhi.controller.AuthController";
}
