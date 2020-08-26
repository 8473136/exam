package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author LiuchangLan
 * @date 2020/7/16 10:28
 */
@Data
@Table(name = "tab_sys_user")
public class UserDTO extends BaseDTO{
    // 用户名
    private String account;

    // 密码
    private String password;

    // 盐
    private String salt;

    // 昵称
    private String nickName;

    // 部门id
    private Integer deptId;

    // 电话
    private String phone;

    // 用户状态 0正常 1未激活
    private Integer userStatus;

    private Integer roleId;
}
