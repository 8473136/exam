package com.guozhi.rvo;

import com.guozhi.dto.QuestionDTO;
import com.guozhi.dto.CorrectPaperFillBlackDTO;
import lombok.Data;

import java.util.List;

/**
 * 题目和答案rvo
 * @author LiuchangLan
 * @date 2020/11/19 10:34
 */
@Data
public class QuestionAnswer extends QuestionDTO {
    // 正确答案 逗号隔开
    private String answers;
    // 填空改卷需要
    private List<CorrectPaperFillBlackDTO> correctPaperFillBlackDTOS;
}
