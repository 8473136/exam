package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.vo.LogVO;


/**
 * @author LiuchangLan
 * @date 2020/7/24 10:15
 */
public interface LogService {
    PageInfo getLogsByPage(LogVO logVO);
}
