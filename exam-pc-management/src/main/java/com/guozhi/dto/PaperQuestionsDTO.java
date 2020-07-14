package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 试卷题目关联DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:35
 */
@Data
@Table(name = "tab_exam_paper_questions")
public class PaperQuestionsDTO extends BaseDTO{
    // 试卷id
    private Integer paperId;

    // 题目id
    private Integer questionId;
}
