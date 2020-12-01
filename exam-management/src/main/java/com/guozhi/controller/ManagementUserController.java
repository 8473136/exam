package com.guozhi.controller;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.service.UserService;
import com.guozhi.vo.PageVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考试用户控制器
 * @author LiuchangLan
 * @date 2020/11/4 10:50
 */
@RestController
@RequestMapping("management/user")
public class ManagementUserController {

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

    /**
     * @description 新增用户
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    @PostMapping("insert")
    Integer insert(@RequestBody UserDTO dto){
        return userService.insert(dto);
    }

    /**
     * @description 根据id删除
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    @DeleteMapping("delete")
    Integer delete(Integer id){
        return userService.delete(id);
    }

    /**
     * @description 修改用户信息
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    @PostMapping("update")
    Integer update(@RequestBody UserDTO userDTO){
        return userService.update(userDTO);
    }

    /**
     * @description 根据id查询
     * @author LiuChangLan
     * @since 2020/11/5 17:06
     */
    @GetMapping("selectById")
    UserDTO selectById(Integer id){
        return userService.selectById(id);
    }
}
