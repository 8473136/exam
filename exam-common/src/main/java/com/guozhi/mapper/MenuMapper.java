package com.guozhi.mapper;

import com.guozhi.dto.MenuDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description 菜单Mapper
 * @author LiuChangLan
 * @since 2020/8/19 15:14
 */
public interface MenuMapper extends Mapper<MenuDTO> {

    /**
     * @description 查询列表（按照排序）
     * @author LiuChangLan
     * @since 2020/8/19 16:43
     */
    List<MenuDTO> getMenusOrder();
}
