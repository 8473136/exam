package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 试卷分数控制DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:38
 */
@Data
@Table(name = "tab_exam_questions_number")
public class QuestionsNumberDTO extends BaseDTO{
    // 试卷id
    private Integer paperId;

    // 题目类型 0单选 1多选 2判断  3填空 4应答题 5案例分析题
    private String questionType;

    // 题目数量
    private Integer questionNumber;

    // 每题分数
    private Double questionScore;

    // 父题id 用于案例分析题子题
    private Integer parentId;
}
