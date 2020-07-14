package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 题目DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:34
 */
@Data
@Table(name = "tab_exam_commit_question")
public class CommitQuestionDTO extends BaseDTO{
    // 试卷用户关联表id
    private Integer paperUserId;

    // 此题是否正确 0正确 1不正确
    private Integer isProper;

    // 此题是否已经修改 0已批改 1未批改
    private Integer isCorrect;
}
