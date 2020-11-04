package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.service.UserService;
import com.guozhi.vo.PageVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试用户控制器
 * @author LiuchangLan
 * @date 2020/11/4 10:50
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @description 获取用户分页列表
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    @GetMapping("getUserListByPage")
    PageInfo<UserDTO> getUserListByPage(PageVO pageVO){
        return userService.getUserListByPage(pageVO);
    }
    /**
     * @description 获取所有用户
     * @author LiuChangLan
     * @since 2020/11/4 10:48
     */
    @GetMapping("getUserAll")
    List<UserDTO> getUserAll(){
        return userService.getUserAll();
    }
}
