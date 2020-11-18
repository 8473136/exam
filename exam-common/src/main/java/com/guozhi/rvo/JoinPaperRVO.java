package com.guozhi.rvo;

import com.guozhi.dto.PaperDTO;
import lombok.Data;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/12 11:35
 */
@Data
public class JoinPaperRVO extends PaperDTO {
    private List<JoinQuestionRVO> questionDTOS;

}
