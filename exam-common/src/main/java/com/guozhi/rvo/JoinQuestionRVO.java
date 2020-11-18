package com.guozhi.rvo;

import com.guozhi.dto.QuestionDTO;
import com.guozhi.dto.QuestionsOptionDTO;
import lombok.Data;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/12 14:27
 */
@Data
public class JoinQuestionRVO extends QuestionDTO {
    // 选项
    private List<QuestionsOptionDTO> options;
    // 题目类型（中文）
    private String questionTypeChinese;
    // 题目分数
    private Double questionScore;
}
