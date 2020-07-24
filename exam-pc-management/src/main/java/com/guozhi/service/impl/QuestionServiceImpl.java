package com.guozhi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.dto.QuestionsOptionDTO;
import com.guozhi.mapper.QuestionMapper;
import com.guozhi.mapper.QuestionOptionMapper;
import com.guozhi.rvo.QuestionRVO;
import com.guozhi.service.QuestionService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.QuestionVO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        questionDTO.setIsDeleted(1);
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
        List<QuestionRVO> qeustions = questionMapper.getQeustions();
        return new PageInfo<>(qeustions);
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> importQuestion(MultipartFile file) throws IOException {
        List<Map<String, Object>> results = new ArrayList<>();
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
            System.out.println(row.getLastCellNum());
            for (int l = 7; l < row.getLastCellNum(); l++) {
                char zm = (char) (l + 58);
                result.put(String.valueOf(zm), row.getCell(l).getStringCellValue());
            }
            results.add(result);
        }
        return results;
    }
}
