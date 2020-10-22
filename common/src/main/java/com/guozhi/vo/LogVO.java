package com.guozhi.vo;

import com.guozhi.vo.PageVO;
import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/7/24 10:11
 */
@Data
public class LogVO extends PageVO{

    // 日志类型
    private Integer logType;

    // 操作模块
    private String module;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;
}