package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.RoleDTO;
import com.guozhi.service.RoleService;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.RoleMenuVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/8/19 18:45
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * @description 查询权限分页列表
     * @author LiuChangLan
     * @since 2020/8/19 18:37
     */
    @GetMapping("getRoleListByPage")
    @TraceLog(module = "权限管理",business = "查询权限列表")
    public PageInfo<RoleDTO> getRoleListByPage(PageVO pageVO){
        return roleService.getRoleListByPage(pageVO);
    }

    /**
     * @description 新增权限
     * @author LiuChangLan
     * @since 2020/8/19 18:37
     */
    @PostMapping("insertRole")
    @TraceLog(module = "权限管理",business = "新增权限列表")
    public Integer insertRole(@RequestBody RoleDTO roleDTO){
        return roleService.insertRole(roleDTO);
    }

    /**
     * @description 删除权限
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    @DeleteMapping("deleteRole")
    @TraceLog(module = "删除权限",business = "删除权限")
    public Integer deleteRole(Integer id){
        return roleService.deleteRole(id);
    }

    /**
     * @description 根据权限id获取权限详细信息
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    @GetMapping("getRoleById")
    @TraceLog(module = "权限管理",business = "查询权限详细信息")
    public RoleDTO getRoleById(Integer id){
        return roleService.getRoleById(id);
    }

    /**
     * @description 修改权限
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    @PostMapping("updateRole")
    @TraceLog(module = "权限管理",business = "修改权限名称、备注")
    public Integer updateRole(@RequestBody RoleDTO roleDTO){
        return roleService.updateRole(roleDTO);
    }

    @PostMapping("setRole")
    @TraceLog(module = "权限管理",business = "修改菜单权限")
    public Integer setRole(@RequestBody RoleMenuVO vo){
        return roleService.setRole(vo);
    }

    @GetMapping("getAllRole")
    @TraceLog(module = "权限管理",business = "获取所有权限")
    public List<RoleDTO> getAllRole(){
        return roleService.getAllRole();
    }

}
