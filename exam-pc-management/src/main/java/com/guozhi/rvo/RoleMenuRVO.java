package com.guozhi.rvo;

import lombok.Data;

import java.util.List;

/**
 * 权限菜单返回值
 * @author LiuchangLan
 * @date 2020/8/24 11:00
 */
@Data
public class RoleMenuRVO {
    // 主键
    private Integer id;

    // 菜单标题
    private String title;

    // 菜单图标
    private String icon;

    // 菜单地址
    private String href;

    // 子菜单
    private List<RoleMenuRVO> children;

    private List<RoleMenuRVO> child;

    // 是否默认选中
    private boolean checked;

    // 父菜单id
    private Integer parentId;
}
