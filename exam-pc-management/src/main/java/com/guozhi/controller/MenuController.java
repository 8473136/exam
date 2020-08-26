package com.guozhi.controller;

import com.guozhi.core.TraceLog;
import com.guozhi.dto.MenuDTO;
import com.guozhi.rvo.RoleMenuRVO;
import com.guozhi.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单控制层
 * @author LiuchangLan
 * @date 2020/8/19 15:22
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/getAllMenus")
    @TraceLog(module = "菜单管理",business = "查询菜单列表")
    public List<MenuDTO> getAllMenus(){
        return menuService.getAllMenus();
    }

    @PostMapping("/addMenu")
    @TraceLog(module = "菜单管理",business = "新增菜单")
    public Integer addMenu(MenuDTO menuDTO){
        return menuService.addMenu(menuDTO);
    }

    @DeleteMapping("delteMenu")
    @TraceLog(module = "菜单管理",business = "删除菜单")
    public Integer deleteMenu(Integer id){
        return menuService.deleteMenu(id);
    }

    @GetMapping("getMenuById")
    @TraceLog(module = "菜单管理",business = "获取菜单详细信息")
    public MenuDTO getMenuById(Integer id){
        return menuService.getMenuById(id);
    }

    @PostMapping("updateMenu")
    @TraceLog(module = "菜单管理",business = "修改菜单")
    public Integer updateMenu(MenuDTO menuDTO){
        return menuService.updateMenu(menuDTO);
    }

    @GetMapping("getMenuTree")
    @TraceLog(module = "菜单管理",business = "获取权限列表菜单")
    public List<RoleMenuRVO> getMenuTree(Integer roleId){
        return menuService.getMenuTree(roleId);
    }
}
