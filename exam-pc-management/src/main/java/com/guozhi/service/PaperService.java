package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.PaperDTO;
import com.guozhi.rvo.PaperRVO;
import com.guozhi.vo.PageVO;

import java.util.List;

/**
 * @description 试卷Service
 * @author LiuChangLan
 * @since 2020/7/15 16:48
 */
public interface PaperService {

    /**
     * @description 添加试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer addPaper(PaperDTO paperDTO);

    /**
     * @description 删除试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer deletePaper(int id);

    /**
     * @description 修改试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    Integer updatePaper(PaperDTO paperDTO);

    /**
     * @description 查询分页试卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    PageInfo<PaperRVO> getPaperByPage(PageVO pageVO);

    /**
     * @description 格局id获取书卷信息
     * @author LiuChangLan
     * @since 2020/7/15 16:20
     */
    PaperDTO getPaperById(String id);
}
