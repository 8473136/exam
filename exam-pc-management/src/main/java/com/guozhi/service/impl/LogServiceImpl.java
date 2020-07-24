package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.LogDTO;
import com.guozhi.mapper.LogMapper;
import com.guozhi.rvo.LogRVO;
import com.guozhi.service.LogService;
import com.guozhi.vo.LogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/24 10:16
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public PageInfo getLogsByPage(LogVO logVO) {
        PageHelper.startPage(logVO.getPageIndex(),logVO.getPageSize());
        List<LogRVO> logs = logMapper.getLogsByTime(logVO);
        return new PageInfo<>(logs);
    }
}
