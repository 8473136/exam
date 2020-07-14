package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.PaperDTO;
import com.guozhi.mapper.PaperMapper;
import com.guozhi.service.PaperService;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.ldap.PagedResultsControl;
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
        return paperMapper.deleteByPrimaryKey(paperDTO);
    }

    /**
     * @description 获取试卷（分页）
     * @author LiuChangLan
     * @since 2020/7/14 10:30
     */
    @Override
    public PageInfo<PaperDTO> getPaperByPage(PageVO pageVO) {
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setIsDeleted(0);
        PageHelper.startPage(pageVO.getPageIndex(), pageVO.getPageSize());
        List<PaperDTO> papers = paperMapper.select(paperDTO);
        return new PageInfo<>(papers);
    }
}
