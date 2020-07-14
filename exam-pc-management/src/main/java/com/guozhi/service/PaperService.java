package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.PaperDTO;
import com.guozhi.vo.PageVO;

import java.util.List;

public interface PaperService {
    Integer addPaper(PaperDTO paperDTO);
    Integer deletePaper(int id);
    PageInfo<PaperDTO> getPaperByPage(PageVO pageVO);
}
