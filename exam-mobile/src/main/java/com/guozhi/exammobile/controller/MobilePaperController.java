package com.guozhi.exammobile.controller;

import com.guozhi.core.BusinessException;
import com.guozhi.core.Result;
import com.guozhi.rvo.JoinPaperRVO;
import com.guozhi.rvo.UserPaperRVO;
import com.guozhi.service.PaperService;
import com.guozhi.vo.SubmitAnswerVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/5 17:57
 */
@RestController
@RequestMapping("mobile/paper")
public class MobilePaperController {

    @Resource
    private PaperService paperService;

    /**
     * @description 获取考试列表
     * @author LiuChangLan
     * @since 2020/11/5 17:58
     */
    @GetMapping("getUserPaper")
    List<UserPaperRVO> getUserPaper(Integer userId){
        return paperService.getUserPaper(userId);
    }

    /**
     * @description 加载试卷信息以及题目
     * @author LiuChangLan
     * @since 2020/11/18 14:54
     */
    @GetMapping("joinPaper")
    JoinPaperRVO joinPaper(Integer paperId,Integer userId){
        return paperService.joinPaper(paperId,userId);
    }

    /**
     * @description 保存题目答案
     * @author LiuChangLan
     * @since 2020/11/18 14:54
     */
    @PostMapping("saveAnswer")
    Result saveAnswer(@RequestBody SubmitAnswerVO submitAnswerVO) throws BusinessException {
        return paperService.saveAnswer(submitAnswerVO);
    }

    /**
     * @description 提交试卷
     * @author LiuChangLan
     * @since 2020/11/18 17:32
     */
    @PostMapping("submitPaper")
    Result submitPaper(@RequestBody SubmitAnswerVO submitAnswerVO) throws BusinessException {
        return paperService.submitPaper(submitAnswerVO);
    }
}
