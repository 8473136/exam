package com.guozhi.rvo;

import com.guozhi.dto.PaperDTO;
import lombok.Data;

/**
 * 用户查看试卷返参
 * @author LiuchangLan
 * @date 2020/11/4 15:09
 */
@Data
public class UserPaperRVO extends PaperDTO {

    // 用户得分
    private Double score;

    // 考试状态 0未考试1已经考试
    private Integer examStatus;

    // 考试题目
    private Integer questionNum;

    // 剩余考试次数
    private Integer remainingCount;

    //  试卷状态
    //  0 未开始
    //  1 已结束 未考试
    //  2 已结束 已考试
    //  3 正常考试
    //  4 已经考试 但剩余考试次数不为0
    //  5 已经考试 剩余考试次数为0
    private Integer examType;
}
