package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.UserDTO;
import com.guozhi.service.UserService;
import com.guozhi.vo.PageVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制层
 * @author LiuchangLan
 * @date 2020/7/16 10:35
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("addUser")
    @TraceLog(module = "用户管理",business = "新增用户")
    public Integer addUser(UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @DeleteMapping("delUser")
    @TraceLog(module = "用户管理",business = "删除用户")
    public Integer delUser(Integer id){
        return userService.delUser(id);
    }

    @PostMapping("updUser")
    @TraceLog(module = "用户管理",business = "编辑用户")
    public Integer updUser(UserDTO userDTO){
        return userService.updUser(userDTO);
    }

    @GetMapping("getUserListByPage")
    @TraceLog(module = "用户管理",business = "获取用户分页列表")
    public PageInfo<UserDTO> getUserListByPage(PageVO pageVO){
        return userService.getUserListByPage(pageVO);
    }

    @GetMapping("getUserById")
    @TraceLog(module = "用户管理",business = "根据用户id查询用户信息")
    public UserDTO getUserById(Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("setUserRole")
    @TraceLog(module = "用户管理",business = "设置用户权限")
    public Integer setUserRole(UserDTO userDTO){
        return userService.setUserRole(userDTO);
    }

}
