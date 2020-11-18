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
}
