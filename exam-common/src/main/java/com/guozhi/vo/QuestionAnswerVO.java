package com.guozhi.vo;

import lombok.Data;

import java.util.List;

/**
 * 提交的答案VO
 * @author LiuchangLan
 * @date 2020/11/18 13:18
 */
@Data
public class QuestionAnswerVO {
    // 题目id
    private Integer questionId;
    // 提交的答案id集合
    private List<Integer> answer;
}
