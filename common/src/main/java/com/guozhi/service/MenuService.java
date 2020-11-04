package com.guozhi.service;

import cn.hutool.core.lang.tree.Tree;
import com.guozhi.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    List<MenuDTO> getAllMenus();

    Integer addMenu(MenuDTO menuDTO);

    Integer deleteMenu(Integer menuDTO);

    MenuDTO getMenuById(Integer id);

    Integer updateMenu(MenuDTO menuDTO);

    List<Tree<String>> getMenuTree(Integer roleId);
}
