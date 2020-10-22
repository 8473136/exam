package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * 权限DTO
 * @author LiuchangLan
 * @date 2020/8/19 18:33
 */
@Data
@Table(name = "tab_sys_role")
public class RoleDTO extends BaseDTO{
    // 权限名称
    private String roleName;
    // 权限描述
    private String remark;
}
