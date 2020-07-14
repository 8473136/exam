package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 题目选项DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:39
 */
@Data
@Table(name = "tab_exam_questions_option")
public class QuestionsOptionDTO extends BaseDTO{
    // 选项内容
    private String optionContent;

    // 题目id
    private Integer questionId;

    // 是否为正确答案 0正确 1错误
    private Integer isRightKey;
}
