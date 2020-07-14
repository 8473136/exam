package com.guozhi.utils.utils;

import java.util.UUID;

/**
 * uuid工具
 * @author LiuchangLan
 * @date 2020/6/27 22:59
 */
public class UUIDUtils {
    public static String ramdomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
