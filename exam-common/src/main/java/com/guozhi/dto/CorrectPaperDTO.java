package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 改卷信息dto
 * @author LiuchangLan
 * @date 2020/11/19 17:04
 */
@Data
@Table(name = "tab_exam_correct_paper")
public class CorrectPaperDTO extends BaseDTO{

    // 试卷用户关联表id
    private Integer paperUserId;

    // 全部改卷信息
    private String correctPaperAllJson;

    // 错误改卷信息
    private String correctPaperErrorJson;

    // 用户得分
    private Double score;

    // 提交id
    private Integer submitId;
}
