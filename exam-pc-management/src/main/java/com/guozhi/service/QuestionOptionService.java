package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.dto.QuestionsOptionDTO;
import com.guozhi.vo.PageVO;

/**
 * 题目选项Service
 * @author LiuchangLan
 * @date 2020/7/15 16:44
 */
public interface QuestionOptionService {

    /**
     * @description 添加题目选项
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer addQuestionOption(QuestionsOptionDTO questionsOptionDTO);

    /**
     * @description 删除题目选项
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer deleteQuestionOption(String id);

    /**
     * @description 修改题目选项
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    Integer updateQuestionOption(QuestionsOptionDTO questionsOptionDTO);

    /**
     * @description 查询题目选项分页
     * @author LiuChangLan
     * @since 2020/7/15 16:46
     */
    PageInfo<QuestionDTO> selectQuestionOption(PageVO pageVO);

}
