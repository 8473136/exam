package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.BusinessException;
import com.guozhi.core.Result;
import com.guozhi.dto.PaperDTO;
import com.guozhi.rvo.JoinPaperRVO;
import com.guozhi.rvo.PaperRVO;
import com.guozhi.rvo.UserPaperRVO;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.PaperImportQuestionVO;
import com.guozhi.vo.ReleasePaperVO;
import com.guozhi.vo.SubmitAnswerVO;

import java.util.List;

/**
 * @description 试卷Service
 * @author LiuChangLan
 * @since 2020/7/15 16:48
 */
public interface PaperService {

    /**
     * @description 添加试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer addPaper(PaperDTO paperDTO);

    /**
     * @description 删除试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer deletePaper(int id);

    /**
     * @description 修改试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer updatePaper(PaperDTO paperDTO);

    /**
     * @description 查询分页试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    PageInfo<PaperRVO> getPaperByPage(PageVO pageVO);

    /**
     * @description 格局id获取书卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    PaperDTO getPaperById(String id);

    /**
     * @description 给试卷添加题目
     * @author LiuChangLan
     * @since 2020/9/2 16:31
     */
    Integer addPaperQuestion(PaperImportQuestionVO paperImportQuestionVO);

    /**
     * @description 获取用户可以考试的试卷
     * @author LiuChangLan
     * @since 2020/11/4 15:07
     */
    List<UserPaperRVO> getUserPaper(Integer userId);

    /**
     * @description 发布试卷
     * @author LiuChangLan
     * @since 2020/11/5 17:33
     */
    Integer releasePaper(ReleasePaperVO vo);

    /**
     * @description 参加考试接口
     * @author LiuChangLan
     * @since 2020/11/12 11:39
     */
    JoinPaperRVO joinPaper(Integer paperId,Integer userId);

    /**
     * @description 保存当前选择的题目
     * @author LiuChangLan
     * @since 2020/11/18 13:20
     * @return
     */
    Result saveAnswer(SubmitAnswerVO submitAnswerVO) throws BusinessException;

    /**
     * @description 提交试卷接口
     * @author LiuChangLan
     * @since 2020/11/18 17:33
     */
    Result submitPaper(SubmitAnswerVO submitAnswerVO) throws BusinessException;
}
