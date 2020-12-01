package com.guozhi.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 提交试卷DTO
 * @author LiuchangLan
 * @date 2020/11/18 17:54
 */
@Data
@Table(name = "tab_exam_submit_paper")
public class SubmitPaperDTO {
    // 主键
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    // 试卷id
    private Integer paperId;
    // 用户id
    private Integer userId;
    // 提交内容Json
    private String commitContent;
    // 提交时间
    private String commitTime;
    // 0提前交卷 1自动交卷
    private Integer commitType;
    // 改卷状态 0未改卷 1改卷中 2改卷完成
    private Integer correctPaperStatus;
}
