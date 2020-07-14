package com.guozhi.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author LiuchangLan
 * @date 2020/6/27 23:11
 */
public class DateUtils {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String currentTime(){
        return simpleDateFormat.format(new Date());
    }

    public static String yMdHmsFormat(Date date){
        return simpleDateFormat.format(date);
    }

}
