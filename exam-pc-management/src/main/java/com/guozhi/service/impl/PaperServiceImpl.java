package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.PaperDTO;
import com.guozhi.mapper.PaperMapper;
import com.guozhi.rvo.PaperRVO;
import com.guozhi.service.PaperService;
import com.guozhi.utils.DateUtils;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/14 10:29
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperMapper paperMapper;

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
        paperDTO.setIsDeleted(1);
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
}
