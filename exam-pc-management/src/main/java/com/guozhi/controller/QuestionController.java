package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.rvo.QuestionRVO;
import com.guozhi.service.QuestionService;
import com.guozhi.vo.PageVO;
import org.apache.ibatis.annotations.DeleteProvider;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.lang.model.type.IntersectionType;

/**
 * @author LiuchangLan
 * @date 2020/7/17 14:18
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping("getQuestionsByPage")
    public PageInfo<QuestionRVO> getQuestionsByPage(PageVO pageVO){
        return questionService.getQuestionsByPage(pageVO);
    }

    @PostMapping("addQuestion")
    public Integer addQuestion(QuestionDTO questionDTO){
        return questionService.addQuestion(questionDTO);
    }

    @DeleteMapping("delQuestion")
    public Integer delQuestion(Integer id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping("getQuestionById")
    public QuestionDTO getQuestionById(Integer id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("updQuestion")
    public Integer updQuestion(QuestionDTO questionDTO){
        return questionService.updateQuestion(questionDTO);
    }

}
