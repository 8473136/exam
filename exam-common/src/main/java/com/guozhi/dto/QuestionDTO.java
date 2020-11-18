package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 题目DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:37
 */
@Data
@Table(name = "tab_exam_questions")
public class QuestionDTO extends BaseDTO{
    // 题目名称
    private String questionName;

    // 题目类型 字典表
    private String questionType;

    // 父题 案例分析-父题id
    private Integer parentQuestionId;

    // 试题出处
    private String questionSource;

    // 题目所属库 0为考试库 1为练习库
    private String questionDepot;

    // 所属专题id
    private String specialId;

    // 题目解析
    private String questionAnalysis;
}
