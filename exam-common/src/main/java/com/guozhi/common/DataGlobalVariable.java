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

    /** 提前交卷 状态值*/
    public static final Integer ADVANCE_COMMIT_PAPER_TYPE = 0;

    /** 自动交卷状态值*/
    public static final Integer AUTO_COMMIT_PAPER_TYPE = 1;

    /** 答案正确*/
    public static final Integer QUESTION_ANSWER_CORRECT = 0;

    /** 答案错误*/
    public static final Integer QUESTION_ANSWER_WRONG = 1;

    /** 答案半对*/
    public static final Integer QUESTION_ANSWER_HALF_PAIR = 2;

    /** 未改卷*/
    public static final Integer CORRECT_PAPER_STATUS_NOT_CORRECT = 0;

    /** 改卷中*/
    public static final Integer CORRECT_PAPER_STATUS_CORRECTING = 1;

    /** 改卷完成*/
    public static final Integer CORRECT_PAPER_STATUS_CORRECTING_END = 2;

    /** 改卷失败*/
    public static final Integer CORRECT_PAPER_STATUS_CORRECTING_ERROR = 3;

    /** 未考试*/
    public static final Integer EXAM_STATUS_NOT_EXAM = 0;

    /** 已经考试*/
    public static final Integer EXAM_STATUS_ALREADY_EXAM = 1;

    /** 正在考试*/
    public static final Integer EXAM_STATUS_TAKING_EXAM = 2;

}
