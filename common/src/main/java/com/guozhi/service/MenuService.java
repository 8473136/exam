package com.guozhi.service;

import com.guozhi.dto.MenuDTO;
import com.guozhi.rvo.RoleMenuRVO;

import java.util.List;

public interface MenuService {

    List<MenuDTO> getAllMenus();

    Integer addMenu(MenuDTO menuDTO);

    Integer deleteMenu(Integer menuDTO);

    MenuDTO getMenuById(Integer id);

    Integer updateMenu(MenuDTO menuDTO);

    List<RoleMenuRVO> getMenuTree(Integer roleId);
}
