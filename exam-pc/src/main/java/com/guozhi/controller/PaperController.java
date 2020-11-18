package com.guozhi.controller;

import com.guozhi.rvo.JoinPaperRVO;
import com.guozhi.rvo.UserPaperRVO;
import com.guozhi.service.PaperService;
import com.guozhi.vo.SubmitAnswerVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/5 17:57
 */
@RestController
@RequestMapping("paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @Autowired
    RabbitTemplate rabbitTemplate;

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
    JoinPaperRVO joinPaper(Integer paperId){
        return paperService.joinPaper(paperId);
    }

    /**
     * @description 保存题目答案
     * @author LiuChangLan
     * @since 2020/11/18 14:54
     */
    @PostMapping("saveAnswer")
    void saveAnswer(@RequestBody SubmitAnswerVO submitAnswerVO){
        paperService.saveAnswer(submitAnswerVO);
    }

    @PostMapping("submitPaper")
    void submitPaper(){
        rabbitTemplate.convertAndSend("correctPaperExchange","currect","message");
    }
}
