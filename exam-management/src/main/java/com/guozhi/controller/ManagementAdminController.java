package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.AdminDTO;
import com.guozhi.service.AdminService;
import com.guozhi.vo.PageVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制层
 * @author LiuchangLan
 * @date 2020/7/16 10:35
 */
@RestController
@RequestMapping("management/admin")
public class ManagementAdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("addAdmin")
    @TraceLog(module = "用户管理",business = "新增用户")
    public Integer addAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.addAdmin(adminDTO);
    }

    @DeleteMapping("delAdmin")
    @TraceLog(module = "用户管理",business = "删除用户")
    public Integer delAdmin(Integer id){
        return adminService.delAdmin(id);
    }

    @PostMapping("updAdmin")
    @TraceLog(module = "用户管理",business = "编辑用户")
    public Integer updAdmin(AdminDTO adminDTO){
        return adminService.updAdmin(adminDTO);
    }

    @GetMapping("getAdminListByPage")
    @TraceLog(module = "用户管理",business = "获取用户分页列表")
    public PageInfo<AdminDTO> getAdminListByPage(PageVO pageVO){
        return adminService.getAdminListByPage(pageVO);
    }

    @GetMapping("getAdminById")
    @TraceLog(module = "用户管理",business = "根据用户id查询用户信息")
    public AdminDTO getAdminById(Integer id){
        return adminService.getAdminById(id);
    }

    @PostMapping("setAdminRole")
    @TraceLog(module = "用户管理",business = "设置用户权限")
    public Integer setAdminRole(AdminDTO adminDTO){
        return adminService.setAdminRole(adminDTO);
    }

}
