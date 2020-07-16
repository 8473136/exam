package com.guozhi.vo;

import lombok.Data;

/**
 * 登录入参
 * @author LiuchangLan
 * @date 2020/7/16 14:35
 */
@Data
public class LoginVO {
    // 用户名
    private String account;
    // 密码
    private String password;
}
