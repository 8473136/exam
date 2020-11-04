package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.PaperDTO;
import com.guozhi.rvo.PaperRVO;
import com.guozhi.service.PaperService;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.PaperImportQuestionVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LiuchangLan
 * @date 2020/7/14 10:51
 */
@RestController
@RequestMapping("paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @PostMapping("addPaper")
    @TraceLog(module = "试卷管理",business = "新增试卷")
    public Integer addPaper(@RequestBody PaperDTO paperDTO) {
        return paperService.addPaper(paperDTO);
    }

    @DeleteMapping("deletePaper")
    @TraceLog(module = "试卷管理",business = "删除试卷")
    public Integer deletePaper(int id) {
        return paperService.deletePaper(id);
    }

    @GetMapping("getPaperByPage")
    @TraceLog(module = "试卷管理",business = "获取试卷分页列表")
    public PageInfo<PaperRVO> getPaperByPage(PageVO pageVO) {
        return paperService.getPaperByPage(pageVO);
    }

    @GetMapping("getPaperById")
    @TraceLog(module = "试卷管理",business = "根据试卷id获取试卷信息")
    public PaperDTO getPaperById(String id){
        return paperService.getPaperById(id);
    }

    @PostMapping("updatePaper")
    @TraceLog(module = "试卷管理",business = "编辑试卷")
    public Integer updatePaper(@RequestBody PaperDTO paperDTO){
        return paperService.updatePaper(paperDTO);
    }

    @PostMapping("addPaperQuestion")
    @TraceLog(module = "试卷管理",business = "给试卷添加题目")
    public Integer addPaperQuestion(@RequestBody PaperImportQuestionVO data){
        return paperService.addPaperQuestion(data);
    }
}
