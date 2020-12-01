package com.guozhi.dto;

import lombok.Data;

import java.util.List;

/**
 * 改卷试卷DTO
 * @author LiuchangLan
 * @date 2020/11/19 11:18
 */
@Data
public class CorrectPaperVO {

    // 试卷id
    private Integer paperId;
    // 用户id
    private Integer userId;
    // 改卷题目信息
    private List<CorrectQuestionVO> questions;
    // 改卷时间
    private String correctTime;
}
