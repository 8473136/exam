package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.vo.PageVO;

public interface UserService {

    /**
     * @description 添加用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer addUser(UserDTO userDTO);

    /**
     * @description 删除用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer delUser(Integer id);

    /**
     * @description 修改用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer updUser(UserDTO userDTO);

    /**
     * @description 获取用户分页列表
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    PageInfo<UserDTO> getUserListByPage(PageVO pageVO);

    /**
     * @description 根据id获取用户信息
     * @author LiuChangLan
     * @since 2020/7/16 10:38
     */
    UserDTO getUserById(Integer id);

    /**
     * @description 设置权限
     * @author LiuChangLan
     * @since 2020/8/24 17:45
     */
    Integer setUserRole(UserDTO userDTO);

}
