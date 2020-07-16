package com.guozhi.common;

import lombok.Data;

/**
 * @author LiuchangLan
 * @date 2020/7/16 15:05
 */
@Data
public class JwtPayload {

    /** 用户 Id */
    private Integer id;

    /** 用户名 */
    private String account;

    /** 昵称 */
    private String nickName;

    /** 部门id */
    private Integer deptId;

}
