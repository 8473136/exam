package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionsDTO;
import com.guozhi.mapper.QuestionMapper;
import com.guozhi.service.QuestionService;
import com.guozhi.utils.DateUtils;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/15 16:48
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Integer addQuestion(QuestionsDTO questionsDTO) {
        return questionMapper.insertSelective(questionsDTO);
    }

    @Override
    public Integer deleteQuestion(Integer id) {
        QuestionsDTO questionsDTO = new QuestionsDTO();
        questionsDTO.setId(id);
        questionsDTO.setUpdateTime(DateUtils.currentDateTime());
        questionsDTO.setIsDeleted(1);
        return questionMapper.updateByPrimaryKeySelective(questionsDTO);
    }

    @Override
    public Integer updateQuestion(QuestionsDTO questionsDTO) {
        questionsDTO.setUpdateTime(DateUtils.currentDateTime());
        return questionMapper.updateByPrimaryKeySelective(questionsDTO);
    }

    @Override
    public PageInfo<QuestionsDTO> selectQuestion(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        QuestionsDTO questionsDTO = new QuestionsDTO();
        questionsDTO.setIsDeleted(0);
        List<QuestionsDTO> list = questionMapper.select(questionsDTO);
        return new PageInfo<>(list);
    }
}
