package com.guozhi.dto;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author LiuchangLan
 * @date 2020/7/24 16:35
 */
@Data
@Table(name = "tab_sys_menu")
public class MenuDTO extends BaseDTO{
    // 菜单标题
    private String title;

    // 菜单图标
    private String icon;

    // 菜单地址
    private String href;

    // 父菜单id
    private Integer parentId;

    private Integer menuOrder;
}
