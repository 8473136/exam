package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.mapper.QuestionMapper;
import com.guozhi.rvo.QuestionRVO;
import com.guozhi.service.QuestionService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
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
    public Integer addQuestion(QuestionDTO questionDTO) {
        questionDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return questionMapper.insertSelective(questionDTO);
    }

    @Override
    public Integer deleteQuestion(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setUpdateTime(DateUtils.currentDateTime());
        questionDTO.setIsDeleted(1);
        questionDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return questionMapper.updateByPrimaryKeySelective(questionDTO);
    }

    @Override
    public Integer updateQuestion(QuestionDTO questionDTO) {
        questionDTO.setUpdateTime(DateUtils.currentDateTime());
        questionDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return questionMapper.updateByPrimaryKeySelective(questionDTO);
    }

    @Override
    public PageInfo<QuestionRVO> getQuestionsByPage(PageVO pageVO) {
        List<QuestionRVO> qeustions = questionMapper.getQeustions();
        return new PageInfo<>(qeustions);
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }
}
