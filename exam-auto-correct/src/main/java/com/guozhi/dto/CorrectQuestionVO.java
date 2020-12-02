package com.guozhi.dto;

import lombok.Data;

import java.util.List;

/**
 * 改卷题目DTO
 * @author LiuchangLan
 * @date 2020/11/19 11:07
 */
@Data
public class CorrectQuestionVO {
    // 题目id
    private Integer questionId;
    // 是否正确 0正确 1不正确 2半对（多选题）
    private Integer isRightKey;
    // 选择的答案集合
    private List<Object> answers;
    // 正确选项数量（多选题用）
    private Integer isRightKeyNum;
    // 题目类型 字典表
    private String questionType;
}
