package com.guozhi.rvo;

import com.guozhi.dto.QuestionDTO;
import lombok.Data;

/**
 * 题目和答案rvo
 * @author LiuchangLan
 * @date 2020/11/19 10:34
 */
@Data
public class QuestionAnswer extends QuestionDTO {
    // 正确答案 逗号隔开
    private String answers;
}
