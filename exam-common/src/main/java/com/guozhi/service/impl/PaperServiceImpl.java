package com.guozhi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.core.BusinessException;
import com.guozhi.core.Result;
import com.guozhi.core.ResultStatusCode;
import com.guozhi.dto.*;
import com.guozhi.mapper.*;
import com.guozhi.rvo.*;
import com.guozhi.service.PaperService;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author LiuchangLan
 * @date 2020/7/14 10:29
 */
@Service
@Slf4j
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    @Resource
    private PaperUserMapper paperUserMapper;

    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Resource
    private QuestionNumberMapper questionNumberMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private SubmitPaperMapper submitPaperMapper;


    @Value("${spring.rabbitmq.correctPaper.queue}")
    private String queueName;
    @Value("${spring.rabbitmq.correctPaper.exchange}")
    private String exchangeName;
    @Value("${spring.rabbitmq.correctPaper.routingKey}")
    private String routingKey;


    /**
     * @description 天机试卷
     * @author LiuChangLan
     * @since 2020/7/14 10:30
     */
    @Override
    public Integer addPaper(PaperDTO paperDTO) {
        return paperMapper.insertSelective(paperDTO);
    }

    /**
     * @description 删除试卷
     * @author LiuChangLan
     * @since 2020/7/14 10:30
     */
    @Override
    public Integer deletePaper(int id) {
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setId(id);
        paperDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
        paperDTO.setUpdateTime(DateUtil.now());
        return paperMapper.updateByPrimaryKeySelective(paperDTO);
    }

    /**
     * @description 修改试卷
     * @author LiuChangLan
     * @since 2020/7/15 16:17
     */
    @Override
    public Integer updatePaper(PaperDTO paperDTO) {
        paperDTO.setUpdateTime(DateUtil.now());
        return paperMapper.updateByPrimaryKeySelective(paperDTO);
    }

    /**
     * @description 获取试卷（分页）
     * @author LiuChangLan
     * @since 2020/7/14 10:30
     */
    @Override
    public PageInfo<PaperRVO> getPaperByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(), pageVO.getPageSize());
        List<PaperRVO> papers = paperMapper.getPaperList();
        return new PageInfo<>(papers);
    }

    /**
     * @description 根据id获取试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:19
     */
    @Override
    public PaperDTO getPaperById(String id) {
        return paperMapper.selectByPrimaryKey(id);
    }

    /**
     * @description 给试卷添加题目
     * @author LiuChangLan
     * @since 2020/9/2 16:31
     */
    @Override
    @Transactional
    public Integer addPaperQuestion(PaperImportQuestionVO paperImportQuestionVO){

        Integer result = -1;

        //需要生成的题目id
        Set<Integer> questionIds = new HashSet<Integer>(paperImportQuestionVO.getFixedQuestion());

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        // 所有题目信息
        final List<QuestionDTO> allQuestion = questionMapper.select(questionDTO);
        // 按照题目类型进行分组
        Map<String, List<QuestionDTO>> collect = allQuestion.stream().collect(Collectors.groupingBy(QuestionDTO::getQuestionType));

        //随机题目
        for (PaperRandomQuestionVO randomQuestionVO : paperImportQuestionVO.getRandomQuestion()) {
            // 获取当前类型的题目
            List<QuestionDTO> questionDTOS = collect.get(randomQuestionVO.getQuestionType());
            // 当前分类的题目大于0时开始随机
            while (questionDTOS.size() > 0 && randomQuestionVO.getQuestionNum() > 0){
                Random random = new Random();
                // 随机数获取下标
                int index = random.nextInt(questionDTOS.size());
                // 获取随机的id
                Integer id = questionDTOS.get(index).getId();
                if (questionIds.add(id)){
                    //随机成功不重复的id跳出循环
                    continue;
                }else {
                    // 遇到重复删除改题目
                    questionDTOS.remove(id);
                    log.info("以及存在题目id为{}",id);
                }
                randomQuestionVO.setQuestionNum(randomQuestionVO.getQuestionNum() - 1);
            }
        }
        // 固定题目
        for (Integer questionId : questionIds) {
            PaperQuestionsDTO paperQuestionsDTO = new PaperQuestionsDTO();
            paperQuestionsDTO.setQuestionId(questionId);
            paperQuestionsDTO.setPaperId(paperImportQuestionVO.getId());
            result = paperQuestionMapper.insertSelective(paperQuestionsDTO);
        }

        for (String questionType : paperImportQuestionVO.getQuestionTypeNumber().keySet()) {
            QuestionsNumberDTO questionsNumberDTO = new QuestionsNumberDTO();
            questionsNumberDTO.setPaperId(paperImportQuestionVO.getId());
            questionsNumberDTO.setQuestionType(questionType);
            questionsNumberDTO.setQuestionScore(Double.parseDouble(String.valueOf(paperImportQuestionVO.getQuestionTypeNumber().get(questionType))));
            questionNumberMapper.insertSelective(questionsNumberDTO);
        }

        PaperDTO paperDTO = BeanUtil.copyProperties(paperImportQuestionVO, PaperDTO.class);
        paperDTO.setUpdateTime(DateUtil.now());
        paperDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        result = paperMapper.updateByPrimaryKeySelective(paperDTO);
        return result;
    }

    /**
     * @description 获取用户可以考试的试卷
     * @author LiuChangLan
     * @since 2020/11/4 15:07
     */
    @Override
    public List<UserPaperRVO> getUserPaper(Integer userId) {
        List<UserPaperRVO> userPaper = paperMapper.getUserPaper(userId);
//        List<UserPaperRVO> result = new ArrayList<>();
//        for (UserPaperRVO userPaperRVO : userPaper) {
//            if (PaperUtils.isNormalExamTime(userPaperRVO)){
//                result.add(userPaperRVO);
//            }
//        }
        return userPaper;
    }


    /**
     * @description 发布试卷
     * @author LiuChangLan
     * @since 2020/11/26 17:24
     */
    @Override
    @Transactional
    public Integer releasePaper(ReleasePaperVO vo) {
        List<Integer> userIds = vo.getUserIds();

        Integer insertCount = 0;

        for (Integer userId : userIds) {
            PaperUserDTO insertDTO = new PaperUserDTO();
            insertDTO.setPaperId(vo.getId());
            insertDTO.setUserId(userId);
            insertDTO.setExamStatus(0);
            insertCount += paperUserMapper.insertSelective(insertDTO);
        }

        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setId(vo.getId());
        paperDTO.setUpdateTime(DateUtil.now());
        paperDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        paperDTO.setPaperStatus(DataGlobalVariable.PAPER_STATUS_PUBLISHED); // 试卷状态 改为已发布
        paperMapper.updateByPrimaryKeySelective(paperDTO);

        return insertCount;
    }

    /**
     * @description 参加考试
     * @author LiuChangLan
     * @since 2020/11/26 17:24
     */
    @Override
    public JoinPaperRVO joinPaper(Integer paperId,Integer userId) {
        // 键
        String key = String.format("user%dpaper%d",userId,paperId);
        String submitAnswerJson = stringRedisTemplate.opsForValue().get(key);
        SubmitAnswerVO submitAnswerVO = null;
        if (!StringUtils.isEmpty(submitAnswerJson)) {
            submitAnswerVO = JSON.parseObject(submitAnswerJson, SubmitAnswerVO.class);
        }

        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setId(paperId);
        paperDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        // 查询试卷信息
        JoinPaperRVO joinPaperRVO = BeanUtil.copyProperties(paperMapper.selectOne(paperDTO),JoinPaperRVO.class);
        // 设置缓存的答案
        joinPaperRVO.setSubmitAnswerVO(submitAnswerVO);
        // 试卷下面的题目信息
        List<JoinQuestionRVO> questionList = new ArrayList<>();
        // 第几题
        int submitAnswerIndex = 0;
        for (QuestionDTO questionsDTO : paperQuestionMapper.selectPaperQuestion(paperId)) {
            JoinQuestionRVO questionRVO = BeanUtil.copyProperties(questionsDTO, JoinQuestionRVO.class);
            QuestionsOptionDTO optionDto = new QuestionsOptionDTO();
            optionDto.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
            optionDto.setQuestionId(questionsDTO.getId());
            // 查询题目下面的选项
            List<QuestionsOptionDTO> opstions = questionOptionMapper.select(optionDto);
            // 最终返回的选项
            List<JoinOptionRVO> joinOptionRVOS = new ArrayList<>();
            // 缓存中保存的答案不为空
            for (QuestionsOptionDTO option : opstions) {
                JoinOptionRVO joinOptionRVO = BeanUtil.copyProperties(option, JoinOptionRVO.class);
                List<Integer> answer = new ArrayList<>();
                if (!DataGlobalVariable.FILL_QUESTION_DICT_CODE.equals(questionsDTO.getQuestionType())) {
                    // 非填空题
                    if (submitAnswerVO != null && submitAnswerVO.getAnswerVOS() != null && submitAnswerVO.getAnswerVOS().get(submitAnswerIndex).getAnswer() != null) {
                        answer = submitAnswerVO.getAnswerVOS().get(submitAnswerIndex).getAnswer().stream().map(item -> Integer.parseInt(String.valueOf(item))).collect(Collectors.toList());
                    }
                    if (answer != null && answer.size() > 0) {
                        if (answer.contains(joinOptionRVO.getId())) {
                            joinOptionRVO.setCheck(true);
                        } else {
                            joinOptionRVO.setCheck(false);
                        }
                    }
                }else {
                    // 填空题
//                    joinOptionRVO.setCheck(false);
                }
                joinOptionRVOS.add(joinOptionRVO);
            }
            questionRVO.setOptions(joinOptionRVOS);
            questionList.add(questionRVO);
            submitAnswerIndex ++;
        }
        joinPaperRVO.setQuestionDTOS(questionList);

        return joinPaperRVO;
    }


    /**
     * @description 缓存现在选择的答案
     * @author LiuChangLan
     * @since 2020/11/26 17:24
     */
    @Override
    public Result saveAnswer(SubmitAnswerVO submitAnswerVO) throws BusinessException {
        try {
            // 试卷详细信息
            PaperDTO paperDTO = paperMapper.selectByPrimaryKey(submitAnswerVO.getPaperId());
            // 缓存时间（考试时间）
            long second = DateUtil.between(DateUtil.parseDateTime(paperDTO.getStartTime()), DateUtil.parseDateTime(paperDTO.getEndTime()), DateUnit.SECOND);
            // 键
            String key = String.format("user%dpaper%d",submitAnswerVO.getUserId(),submitAnswerVO.getPaperId());
            // 存入值
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(submitAnswerVO),second, TimeUnit.SECONDS);
            return new Result(ResultStatusCode.PAPER_SAVE_SUCCESS);
        } catch (Exception e) {
            throw new BusinessException(ResultStatusCode.PAPER_SAVE_ERROR);
        }
    }

    /**
     * @description 提交试卷
     * @author LiuChangLan
     * @since 2020/11/26 17:24
     */
    @Override
    public Result submitPaper(SubmitAnswerVO submitAnswerVO) throws BusinessException {
        // 提交的json内容
        String commitJson = null;
        try {
            commitJson = JSON.toJSONString(submitAnswerVO);
            SubmitPaperDTO submitDTO = new SubmitPaperDTO();
            // 用户id
            submitDTO.setUserId(submitAnswerVO.getUserId());
            // 试卷id
            submitDTO.setPaperId(submitAnswerVO.getPaperId());
            // 提交内容
            submitDTO.setCommitContent(commitJson);
            // 交卷类型
            submitDTO.setCommitType(submitAnswerVO.getCommitType());
            // 插入交卷记录
            submitPaperMapper.insertSelective(submitDTO);

            // 修改该用户已参加过这场考试
            PaperUserDTO paperUserDTO = new PaperUserDTO();
            paperUserDTO.setExamStatus(DataGlobalVariable.EXAM_STATUS_ALREADY_EXAM);

            Example example = new Example(PaperUserDTO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId", submitAnswerVO.getUserId());
            criteria.andEqualTo("paperId", submitAnswerVO.getPaperId());
            // 根据userid 和 paperid修改状态
            paperUserMapper.updateByExampleSelective(paperUserDTO,example);

            // 发送改卷消息到队列
            rabbitTemplate.convertAndSend(exchangeName,routingKey,JSON.toJSONString(submitDTO));
            log.info("在{}用户{}发起了试卷{}的提交[提交成功]，提交内容为：{}",DateUtil.now(),submitAnswerVO.getUserId(),submitAnswerVO.getPaperId(),commitJson);
            // 删除已经缓存的答案
            String key = String.format("user%dpaper%d",submitAnswerVO.getUserId(),submitAnswerVO.getPaperId());
            stringRedisTemplate.delete(key);
            return new Result(ResultStatusCode.PAPER_COMMIT_SUCCESS);
        } catch (Exception e) {
            log.error("在{}用户{}发起了试卷{}的提交[提交失败]，提交内容为：{}",DateUtil.now(),submitAnswerVO.getUserId(),submitAnswerVO.getPaperId(),commitJson,e);
            throw new BusinessException(ResultStatusCode.PAPER_COMMIT_ERROR);
        }
    }
}
