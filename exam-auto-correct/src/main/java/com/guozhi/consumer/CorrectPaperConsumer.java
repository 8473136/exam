package com.guozhi.consumer;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.core.BusinessException;
import com.guozhi.dto.*;
import com.guozhi.mapper.*;
import com.guozhi.rvo.QuestionAnswer;
import com.guozhi.vo.QuestionAnswerVO;
import com.guozhi.vo.SubmitAnswerVO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 改卷队列消费者
 * @author LiuchangLan
 * @date 2020/11/18 16:47
 */
@Component
@RabbitListener(queues = "${spring.rabbitmq.correctPaper.queue}",containerFactory = "customContainerFactory")
@Slf4j
public class CorrectPaperConsumer {

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private PaperUserMapper paperUserMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionNumberMapper questionNumberMapper;

    @Resource
    private CorrectPaperMapper correctPaperMapper;

    @Resource
    private SubmitPaperMapper submitPaperMapper;

    /**
     * @description 处理队列消息
     * @author LiuChangLan
     * @since 2020/11/19 17:43
     * @param message rabbitmq消息
     */
    @RabbitHandler
    public void process(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, BusinessException {
        SubmitPaperDTO submitPaperDTO = JSON.parseObject(message, SubmitPaperDTO.class);
        try {
            // 修改为改卷中
            submitPaperDTO.setCorrectPaperStatus(DataGlobalVariable.CORRECT_PAPER_STATUS_CORRECTING);
            submitPaperMapper.updateByPrimaryKeySelective(submitPaperDTO);
            // 提交的题目信息
            SubmitAnswerVO submitAnswerVO = JSON.parseObject(submitPaperDTO.getCommitContent(), SubmitAnswerVO.class);
            // 改卷
            CorrectPaperVO correctPaperVO = this.correctPaper(submitAnswerVO);
            // 计算总分
            Double allScore = this.calcScore(correctPaperVO);
            // 改卷信息、分数入库


            // 查询用户和试卷关系表id 后面用
            PaperUserDTO paperUserParamDTO = new PaperUserDTO();
            paperUserParamDTO.setUserId(submitAnswerVO.getUserId());
            paperUserParamDTO.setPaperId(submitAnswerVO.getPaperId());
            paperUserParamDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
            PaperUserDTO paperUserDTO = paperUserMapper.selectOne(paperUserParamDTO);

            CorrectPaperDTO correctPaperDTO = new CorrectPaperDTO();
            correctPaperDTO.setScore(allScore);
            correctPaperDTO.setSubmitId(submitPaperDTO.getId());
            correctPaperDTO.setCorrectPaperAllJson(JSON.toJSONString(correctPaperVO));
            correctPaperDTO.setPaperUserId(paperUserDTO.getId());
            correctPaperMapper.insertSelective(correctPaperDTO);

            // 修改为改卷成功
            submitPaperDTO.setCorrectPaperStatus(DataGlobalVariable.CORRECT_PAPER_STATUS_CORRECTING_END);
            submitPaperMapper.updateByPrimaryKeySelective(submitPaperDTO);
            log.info("试卷：{},用户：{},在{},[改卷完成]，改卷内容为：{}",submitPaperDTO.getPaperId(),submitPaperDTO.getUserId(),DateUtil.now(),message);
            // 确认消费
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // 改卷错误
            // 修改为改卷中
            submitPaperDTO.setCorrectPaperStatus(DataGlobalVariable.CORRECT_PAPER_STATUS_CORRECTING_ERROR);
            submitPaperMapper.updateByPrimaryKeySelective(submitPaperDTO);
            log.error("试卷：{},用户：{},在{},[改卷错误]，改卷内容为：{}",submitPaperDTO.getPaperId(),submitPaperDTO.getUserId(),DateUtil.now(),message,e);
            // 拒绝消费
            channel.basicReject(tag, false);
//            channel.basicNack(tag, false,true);
            throw new BusinessException(500,"改卷失败");
        }
    }

    /**
     * @description 改卷
     * @author LiuChangLan
     * @since 2020/11/19 11:31
     * @param submitInfo 交卷信息
     */
    private CorrectPaperVO correctPaper(SubmitAnswerVO submitInfo){
        log.info("改卷中");
        // 存储改卷信息
        CorrectPaperVO correctPaperVO = new CorrectPaperVO();
        correctPaperVO.setUserId(submitInfo.getUserId());
        correctPaperVO.setPaperId(submitInfo.getPaperId());
        correctPaperVO.setCorrectTime(DateUtil.now());
        List<CorrectQuestionVO> correctQuestionVOS = new ArrayList<>();

        // 获取所有题目 以及正确答案
        List<QuestionAnswer> questionAnswer = questionMapper.getQuestionAnswer();
        for (QuestionAnswer answer : questionAnswer) {
            for (QuestionAnswerVO submitQuestion : submitInfo.getAnswerVOS()) {
                // 找到对应题目
                if (answer.getId().equals(submitQuestion.getQuestionId())){
                    // 改卷题目信息
                    CorrectQuestionVO correctQuestionVO = new CorrectQuestionVO();
                    // 题目类型方便后面算分
                    correctQuestionVO.setQuestionType(answer.getQuestionType());
                    // 题目id
                    correctQuestionVO.setQuestionId(submitQuestion.getQuestionId());
                    // 提交的答案
                    List<Integer> submitAnswer = submitQuestion.getAnswer();
                    correctQuestionVO.setAnswers(submitAnswer);
                    // 排序
                    Collections.sort(submitAnswer);
                    // 提交的答案字符串
                    String submitAnswerStr = CollectionUtil.join(submitAnswer,",");
                    // 正确答案字符串
                    String answersStr = answer.getAnswers();
                    // 判断答案是否正确
                    if (submitAnswerStr.equals(answersStr)){
                        // 正确
                        correctQuestionVO.setIsRightKey(DataGlobalVariable.QUESTION_ANSWER_CORRECT);
                    }else {
                        //不正确
                        if (DataGlobalVariable.MULTIPLE_QUESTION_DICT_CODE.equals(answer.getQuestionType())){
                            // 多选题 不全对
                            // 正确答案集合
                            List<String> answerLists = Arrays.asList(answersStr.trim().split(","));
                            // 统计正确数量
                            Integer isRightKeyNum = 0;
                            for (String answerItem : answerLists) {
                                for (Integer submitItem : submitAnswer) {
                                    // 某一个选择正确
                                    if (Integer.parseInt(answerItem) == submitItem){
                                        isRightKeyNum ++;
                                    }
                                }
                            }
                            // 设置正确选项数
                            correctQuestionVO.setIsRightKeyNum(isRightKeyNum);
                            correctQuestionVO.setIsRightKey(DataGlobalVariable.QUESTION_ANSWER_HALF_PAIR);
                        }else {
                            // 其他题 不正确
                            correctQuestionVO.setIsRightKey(DataGlobalVariable.QUESTION_ANSWER_WRONG);
                        }
                    }
                    correctQuestionVOS.add(correctQuestionVO);
                }
            }
        }
        correctPaperVO.setQuestions(correctQuestionVOS);
        log.info("改卷结果:{}",JSON.toJSONString(correctPaperVO));
        return correctPaperVO;
    }

    /**
     * @description 算分
     * @author LiuChangLan
     * @since 2020/11/19 17:24
     * @param correctPaperVO 已经改卷后的结果
     */
    private Double calcScore(CorrectPaperVO correctPaperVO){
        log.info("计算分数中");
        // 获取算分规则
        QuestionsNumberDTO questionsNumberDTO = new QuestionsNumberDTO();
        questionsNumberDTO.setPaperId(correctPaperVO.getPaperId());
        questionsNumberDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<QuestionsNumberDTO> questionsNumbers = questionNumberMapper.select(questionsNumberDTO);

        // 总得分
        Double allScore = 0.0;
        for (CorrectQuestionVO question : correctPaperVO.getQuestions()) {
            for (QuestionsNumberDTO questionsNumber : questionsNumbers) {
                // 找到同类型的题目
                if ((question.getQuestionType().equals(questionsNumber.getQuestionType()))) {
                    if (question.getIsRightKey().equals(DataGlobalVariable.QUESTION_ANSWER_CORRECT)){
                        // 答案正确
                        allScore += questionsNumber.getQuestionScore();
                    }else if (question.getIsRightKey().equals(DataGlobalVariable.QUESTION_ANSWER_HALF_PAIR)){
                        // 半对
                        // TODO: 2020/11/19 半对得分计算
                    }
                }
            }
        }
        return allScore;
    }
}
