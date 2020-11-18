package com.guozhi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.dto.QuestionsOptionDTO;
import com.guozhi.mapper.QuestionMapper;
import com.guozhi.mapper.QuestionOptionMapper;
import com.guozhi.rvo.QuestionRVO;
import com.guozhi.service.QuestionService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
import com.guozhi.utils.UUIDUtils;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.QuestionVO;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuchangLan
 * @date 2020/7/15 16:48
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public Integer addQuestion(QuestionVO questionVO) {
        Integer result = -1;
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(questionVO, questionDTO);
        questionDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        result = questionMapper.insertSelective(questionDTO);
        List<QuestionsOptionDTO> questionsOptionDTOS = JSON.parseObject(questionVO.getOptions(), new TypeReference<List<QuestionsOptionDTO>>() {
        });
        questionsOptionDTOS.forEach(item -> {
            item.setQuestionId(questionDTO.getId());
            item.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
            questionOptionMapper.insertSelective(item);
        });
        return result;
    }

    @Override
    public Integer deleteQuestion(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setUpdateTime(DateUtils.currentDateTime());
        questionDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
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
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        List<QuestionRVO> qeustions = questionMapper.getQeustions();
        return new PageInfo<>(qeustions);
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> analyzeQuestion(MultipartFile file) throws IOException {
        Map<String, Object> results = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        // 输入流
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheetAt = workbook.getSheetAt(0);
//        row:
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            Map<String, Object> result = new HashMap<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
//                if (cell.getStringCellValue() == null || cell.getStringCellValue().trim().equals("")) {
//                    continue row;
//                }
            }
            result.put("questionName", row.getCell(0).getStringCellValue());
            result.put("questionType", row.getCell(1).getStringCellValue());
            result.put("questionAnalysis", row.getCell(2).getStringCellValue());
            result.put("questionSource", row.getCell(3).getStringCellValue());
            result.put("specialId", row.getCell(4).getStringCellValue());
            result.put("questionDepot", row.getCell(5).getStringCellValue());
            result.put("answer", row.getCell(6).getStringCellValue());
            for (int l = 7; l < row.getLastCellNum(); l++) {
                char zm = (char) (l + 58);
                result.put(String.valueOf(zm), row.getCell(l).getStringCellValue());
            }
            list.add(result);
        }
        // 这条纪录的唯一标识
        String uuid = UUIDUtils.ramdomUUID();
        // 放入缓存中
        results.put("list", list);
        stringRedisTemplate.opsForValue().set(uuid, JSONObject.toJSONString(list), 24, TimeUnit.HOURS);
        results.put("uuid", uuid);
        return results;
    }

    @Override
    @Transactional
    public void importQuestion(String uuid) {
        String json = stringRedisTemplate.opsForValue().get(uuid);
        List<Map> questions = JSON.parseArray(json, Map.class);
        for (Map question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            String questionType = question.get("questionType").toString();
            questionDTO.setQuestionName(String.valueOf(question.get("questionName")));
            questionDTO.setQuestionType(questionType);
            questionDTO.setSpecialId(String.valueOf(question.get("specialId")));
            questionDTO.setQuestionAnalysis(String.valueOf(question.get("questionAnalysis")));
            questionDTO.setQuestionSource(String.valueOf(question.get("questionSource")));
            questionDTO.setQuestionDepot(String.valueOf(question.get("questionDepot")));
            questionDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
            // 添加题目
            questionMapper.insertSelective(questionDTO);
            // 选项数量 7个是固定字段 多余的就是选项的个数
            int optionSize = question.keySet().size() - 7;
            // 正确答案
            List<String> answerList = Arrays.asList(question.get("answer").toString().split("##"));
            for (int i = 0; i < optionSize; i++) {
                char optionKey = (char) (i + 65);
                Integer isRightKey = answerList.contains(String.valueOf(optionKey)) ? 0 : 1;
                QuestionsOptionDTO questionsOptionDTO = new QuestionsOptionDTO();
                // 选项内容
                questionsOptionDTO.setOptionContent(String.valueOf(question.get(String.valueOf(optionKey))));
                // 选项id
                questionsOptionDTO.setQuestionId(questionDTO.getId());
                // 是否为正确答案
                questionsOptionDTO.setIsRightKey(isRightKey);
                // 创建人
                questionsOptionDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
                // 添加选项
                questionOptionMapper.insertSelective(questionsOptionDTO);
            }
        }
    }


}
