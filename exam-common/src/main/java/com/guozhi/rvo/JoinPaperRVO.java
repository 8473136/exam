package com.guozhi.rvo;

import com.guozhi.dto.PaperDTO;
import com.guozhi.vo.SubmitAnswerVO;
import lombok.Data;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/12 11:35
 */
@Data
public class JoinPaperRVO extends PaperDTO {
    // 试卷题目集合
    private List<JoinQuestionRVO> questionDTOS;
    // 缓存中保存的答案
    private SubmitAnswerVO submitAnswerVO;
}
