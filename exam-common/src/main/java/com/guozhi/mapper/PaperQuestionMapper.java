package com.guozhi.mapper;

import com.guozhi.dto.PaperQuestionsDTO;
import com.guozhi.rvo.JoinQuestionRVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PaperQuestionMapper extends Mapper<PaperQuestionsDTO> {

    List<JoinQuestionRVO> selectPaperQuestion(Integer paperId);
}
