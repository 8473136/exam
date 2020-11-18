package com.guozhi.rvo;

import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/7/16 9:56
 */
@Data
public class PaperRVO {
    // id
    private Integer id;
    // 试卷名称
    private String paperName;

    //试卷类型名称
    private String paperTypeName;

    // 总分
    private Double totalScore;

    // 合格分
    private Double passScore;

    // 试卷状态 0-未发布 1-已发布
    private Integer paperStatus;

    // 可考次数
    private Integer examCount;

    // 考试时长
    private Integer examDuration;

    //创建时间
    private String createTime;
}
