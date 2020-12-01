package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.service.LogService;
import com.guozhi.vo.LogVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 日志控制器
 * @author LiuchangLan
 * @date 2020/7/24 10:09
 */
@RestController
@RequestMapping("management/log")
public class ManagementLogController {

    @Resource
    private LogService logService;

    @GetMapping("getLogsByPage")
    @TraceLog(module = "日志模块",business = "查看日志")
    public PageInfo getLogsByPage(LogVO logVO){
        return logService.getLogsByPage(logVO);
    }

}
