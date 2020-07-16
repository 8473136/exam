package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
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
    public Integer addUser(UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @DeleteMapping("delUser")
    public Integer delUser(Integer id){
        return userService.delUser(id);
    }

    @PostMapping("updUser")
    public Integer updUser(UserDTO userDTO){
        return userService.updUser(userDTO);
    }

    @GetMapping("getUserListByPage")
    public PageInfo<UserDTO> getUserListByPage(PageVO pageVO){
        return userService.getUserListByPage(pageVO);
    }

    @GetMapping("getUserById")
    public UserDTO getUserById(Integer id){
        return userService.getUserById(id);
    }

}
