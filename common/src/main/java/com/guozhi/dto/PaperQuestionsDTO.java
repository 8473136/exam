package com.guozhi.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 试卷题目关联DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:35
 */
@Data
@Table(name = "tab_exam_paper_questions")
public class PaperQuestionsDTO{

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    // 试卷id
    private Integer paperId;

    // 题目id
    private Integer questionId;
}
