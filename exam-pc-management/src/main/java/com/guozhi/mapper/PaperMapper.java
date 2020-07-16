package com.guozhi.mapper;

import com.guozhi.dto.PaperDTO;
import com.guozhi.rvo.PaperRVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PaperMapper extends Mapper<PaperDTO> {

    /**
     * @description 获取试卷列表信息
     * @author LiuChangLan
     * @since 2020/7/16 10:00
     */
    List<PaperRVO> getPaperList();
}
