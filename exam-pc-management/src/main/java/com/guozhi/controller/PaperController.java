package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.PaperDTO;
import com.guozhi.mapper.PaperMapper;
import com.guozhi.service.PaperService;
import com.guozhi.vo.PageVO;
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
    public Integer addPaper(PaperDTO paperDTO) {
        return paperService.addPaper(paperDTO);
    }

    @DeleteMapping("deletePaper")
    public Integer deletePaper(int id) {
        return paperService.deletePaper(id);
    }

    @GetMapping("getPaperByPage")
    public PageInfo<PaperDTO> getPaperByPage(PageVO pageVO) {
        return paperService.getPaperByPage(pageVO);
    }
}
