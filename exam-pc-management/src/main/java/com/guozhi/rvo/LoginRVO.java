package com.guozhi.rvo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/7/16 14:36
 */
@Data
@AllArgsConstructor
public class LoginRVO {
    private String accessToken;    // jwt token
    private String refreshToken;   // 用户刷新 jwt 的 token
    private Long createTime;       // 创建时间
}
