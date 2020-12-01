package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 试卷用户关联DTO
 * @author LiuchangLan
 * @date 2020/7/13 11:36
 */
@Data
@Table(name = "tab_exam_paper_user")
public class PaperUserDTO extends BaseDTO{
    // 试卷id
    private Integer paperId;

    // 用户id
    private Integer userId;

    // 考试状态 0未考试1提前交卷2自动交卷
    private Integer examStatus;
}
