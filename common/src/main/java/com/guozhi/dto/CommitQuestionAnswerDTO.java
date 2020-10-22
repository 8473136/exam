package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 提交题目DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:35
 */
@Data
@Table(name = "tab_exam_commit_question_answer")
public class CommitQuestionAnswerDTO extends BaseDTO{
    // 提交的题目id
    private Integer commitId;

    // 提交的选项id
    private Integer optionId;
}
