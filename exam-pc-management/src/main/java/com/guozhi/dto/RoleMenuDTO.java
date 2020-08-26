package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author LiuchangLan
 * @date 2020/8/21 14:14
 */
@Data
@Table(name = "tab_sys_role_menu")
public class RoleMenuDTO {
    private Integer id;
    private Integer roleId;
    private Integer menuId;

}
