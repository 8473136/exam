package com.guozhi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.*;
import com.guozhi.mapper.*;
import com.guozhi.rvo.JoinPaperRVO;
import com.guozhi.rvo.JoinQuestionRVO;
import com.guozhi.rvo.PaperRVO;
import com.guozhi.rvo.UserPaperRVO;
import com.guozhi.service.PaperService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        paperDTO.setUpdateTime(DateUtils.currentDateTime());
        return paperMapper.updateByPrimaryKeySelective(paperDTO);
    }

    /**
     * @description 修改试卷
     * @author LiuChangLan
     * @since 2020/7/15 16:17
     */
    @Override
    public Integer updatePaper(PaperDTO paperDTO) {
        paperDTO.setUpdateTime(DateUtils.currentDateTime());
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
        paperDTO.setUpdateTime(DateUtils.currentDateTime());
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
        return paperMapper.getUserPaper(userId);
    }


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
        return insertCount;
    }

    @Override
    public JoinPaperRVO joinPaper(Integer id) {
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setId(id);
        paperDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        // 查询试卷信息
        JoinPaperRVO joinPaperRVO = BeanUtil.copyProperties(paperMapper.selectOne(paperDTO),JoinPaperRVO.class);
        // 试卷下面的题目信息
        List<JoinQuestionRVO> questionList = new ArrayList<>();

        for (QuestionDTO questionsDTO : paperQuestionMapper.selectPaperQuestion(id)) {
            JoinQuestionRVO questionRVO = BeanUtil.copyProperties(questionsDTO, JoinQuestionRVO.class);
            QuestionsOptionDTO questionsOptionDTO = new QuestionsOptionDTO();
            questionsOptionDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
            questionsOptionDTO.setQuestionId(questionsDTO.getId());
            // 查询题目下面的选项
            questionRVO.setOptions(questionOptionMapper.select(questionsOptionDTO));
            questionList.add(questionRVO);
        }
        joinPaperRVO.setQuestionDTOS(questionList);

        return joinPaperRVO;
    }

    @Override
    public void saveAnswer(SubmitAnswerVO submitAnswerVO) {
        // 试卷详细信息
        PaperDTO paperDTO = paperMapper.selectByPrimaryKey(submitAnswerVO.getPaperId());
        // 缓存时间（考试时间）
        long second = DateUtil.between(DateUtil.parseDateTime(paperDTO.getStartTime()), DateUtil.parseDateTime(paperDTO.getEndTime()), DateUnit.SECOND);
        // 键
        String key = String.format("user%dpaper%d",submitAnswerVO.getUserId(),submitAnswerVO.getPaperId());
        // 存入值
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(submitAnswerVO),second, TimeUnit.SECONDS);
    }
}
