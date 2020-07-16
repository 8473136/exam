package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionsDTO;
import com.guozhi.vo.PageVO;

import java.util.List;

/**
 * 题目Service
 * @author LiuchangLan
 * @date 2020/7/15 16:44
 */
public interface QuestionService {

    /**
     * @description 添加题目
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer addQuestion(QuestionsDTO questionsDTO);

    /**
     * @description 删除题目
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer deleteQuestion(Integer id);

    /**
     * @description 修改题目
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer updateQuestion(QuestionsDTO questionsDTO);

    /**
     * @description 查询题目分页
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    PageInfo<QuestionsDTO> selectQuestion(PageVO pageVO);
}
