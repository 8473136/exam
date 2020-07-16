package com.guozhi.service.impl;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionsDTO;
import com.guozhi.dto.QuestionsOptionDTO;
import com.guozhi.mapper.QuestionOptionMapper;
import com.guozhi.service.QuestionOptionService;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/15 16:48
 */
@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {

    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Override
    public Integer addQuestionOption(QuestionsOptionDTO questionsOptionDTO) {
        return null;
    }

    @Override
    public Integer deleteQuestionOption(String id) {
        return null;
    }

    @Override
    public Integer updateQuestionOption(QuestionsOptionDTO questionsOptionDTO) {
        return null;
    }

    @Override
    public PageInfo<QuestionsDTO> selectQuestionOption(PageVO pageVO) {
        return null;
    }
}
