package com.guozhi.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.guozhi.dto.PaperDTO;

/**
 * 试卷工具类
 * @author LiuchangLan
 * @date 2020/11/26 17:27
 */
public class PaperUtils {

    /**
     * @description 是否在正常考试试卷内 不在返回false
     * @author LiuChangLan
     * @since 2020/11/26 17:29
     * @param startTimeStr 考试开始时间
     * @param endTimeStr 考试结束时间
     */
    public static boolean isNormalExamTime(String startTimeStr,String endTimeStr){
        // 考试开始时间
        DateTime startTime = DateUtil.parse(startTimeStr);
        // 考试结束时间
        DateTime endTime = DateUtil.parse(endTimeStr);
        // 当前时间
        DateTime date = DateUtil.date();
        return !date.before(startTime) && !date.after(endTime);
    }

    /**
     * @description 是否在正常考试试卷内 不在返回false
     * @author LiuChangLan
     * @since 2020/11/26 17:29
     * @param paperDTO 试卷dto
     */
    public static boolean isNormalExamTime(PaperDTO paperDTO){
        return isNormalExamTime(paperDTO.getStartTime(),paperDTO.getEndTime());
    }

}
