package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.QuestionDTO;
import com.guozhi.rvo.QuestionRVO;
import com.guozhi.service.QuestionService;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.QuestionVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 题目控制器
 * @author LiuchangLan
 * @date 2020/7/17 14:18
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping("getQuestionsByPage")
    @TraceLog(module = "题目管理",business = "获取题目分页列表")
    public PageInfo<QuestionRVO> getQuestionsByPage(PageVO pageVO){
        return questionService.getQuestionsByPage(pageVO);
    }

    @PostMapping("addQuestion")
    @TraceLog(module = "题目管理",business = "添加题目")
    public Integer addQuestion(@RequestBody QuestionVO questionVO){
        return questionService.addQuestion(questionVO);
    }

    @DeleteMapping("delQuestion")
    @TraceLog(module = "题目管理",business = "删除题目")
    public Integer delQuestion(Integer id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping("getQuestionById")
    @TraceLog(module = "题目管理",business = "根据题目id获取题目信息")
    public QuestionDTO getQuestionById(Integer id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("updQuestion")
    @TraceLog(module = "题目管理",business = "编辑题目")
    public Integer updQuestion(@RequestBody QuestionDTO questionDTO){
        return questionService.updateQuestion(questionDTO);
    }

    @PostMapping("analyzeQuestion")
//    @TraceLog(module = "题目管理",business = "解析批量导入题目文件")
    public Map<String,Object> analyzeQuestion(MultipartFile file) throws IOException {
        return questionService.analyzeQuestion(file);
    }

    @PostMapping("importQuestion")
    @TraceLog(module = "题目管理",business = "导入题目")
    public void importQuestion(String uuid){
        questionService.importQuestion(uuid);
    }

}
