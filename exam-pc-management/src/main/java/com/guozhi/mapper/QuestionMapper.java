package com.guozhi.mapper;

import com.guozhi.dto.QuestionDTO;
import com.guozhi.rvo.QuestionRVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface QuestionMapper extends Mapper<QuestionDTO> {

    List<QuestionRVO> getQeustions();

}
