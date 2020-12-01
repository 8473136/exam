package com.guozhi.mapper;

import com.guozhi.dto.QuestionDTO;
import com.guozhi.rvo.QuestionAnswer;
import com.guozhi.rvo.QuestionRVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface QuestionMapper extends Mapper<QuestionDTO> {


    List<QuestionRVO> getQeustions();

    /**
     * @description 获取题目和答案
     * @author LiuChangLan
     * @since 2020/11/19 10:36
     */
    List<QuestionAnswer> getQuestionAnswer();

}
