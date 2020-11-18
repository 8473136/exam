package com.guozhi.common;

/**
 * 全局常量
 * @author LiuchangLan
 * @date 2020/7/14 15:00
 */
public class DataGlobalVariable {

    /** 用户激活状态码*/
    public static final Integer USER_STATUS_DISABLE = 1;

    /** 删除状态值*/
    public static final Integer IS_DELETE = 1;

    /** 未删除*/
    public static final Integer IS_NOT_DELETE = 0;

    /** 载体在服务间传递时，header 中使用的名称*/
    public static final String HEADER_ACCCESS_TOKEN = "accessToken";

    /** accessToken有效期*/
    public static final long ACCESS_TOKEN_EFFECTIVE = 2 * 60 * 1000L;

    /** 单选题 字典表 id*/
    public static final String SINGLE_QUESTION_DICT_CODE = "DANXUANTI";

    /** 多选题 字典表 id*/
    public static final String MULTIPLE_QUESTION_DICT_CODE = "DUOXUANTI";

    /** 判断题 字典表 id*/
    public static final String JUDGE_QUESTION_DICT_CODE = "PANDUANTI";

    /** 填空题 字典表 id*/
    public static final String FILL_QUESTION_DICT_CODE = "TIANKONGTI";

    /** 操作日志 字典表 id*/
    public static final String EXEC_LOG_DICT_CODE = "CAOZUORIZHI";

    /** 错误日志 字典表 id*/
    public static final String EXCEPTION_LOG_DICT_CODE = "CUOWURIZHI";

    /** 登录授权的路径*/
    public static final String AUTH_CLASS_PATH = "com.guozhi.controller.AuthController";
}
