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

    // 考试状态 0未考试1提前交卷2自动交卷
    private Integer examStatus;
}
