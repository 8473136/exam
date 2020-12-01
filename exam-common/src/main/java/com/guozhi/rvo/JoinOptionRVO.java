package com.guozhi.rvo;

import com.guozhi.dto.QuestionsOptionDTO;
import lombok.Data;

/**
 * 参加考试返回题目选项rvo
 * @author LiuchangLan
 * @date 2020/11/25 17:46
 */
@Data
public class JoinOptionRVO extends QuestionsOptionDTO {
    private boolean check;
}
