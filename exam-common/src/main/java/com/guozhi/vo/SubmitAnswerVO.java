package com.guozhi.vo;

import lombok.Data;

import java.util.List;

/**
 * 提交的试卷VO
 * @author LiuchangLan
 * @date 2020/11/18 13:23
 */
@Data
public class SubmitAnswerVO {
    // 用户id
    private Integer userId;
    // 试卷id
    private Integer paperId;
    // 提交的题目
    private List<QuestionAnswerVO> answerVOS;
    // 0提前交卷 1自动交卷
    private Integer commitType;
    // 剩余考试时间（分钟）
    private Double examTime;
}
