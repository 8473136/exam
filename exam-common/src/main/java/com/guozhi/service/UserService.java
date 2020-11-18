package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.vo.PageVO;

import java.util.List;

public interface UserService {


    /**
     * @description 获取用户分页列表
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    PageInfo<UserDTO> getUserListByPage(PageVO pageVO);

    /**
     * @description 获取所有用户
     * @author LiuChangLan
     * @since 2020/11/4 10:48
     */
    List<UserDTO> getUserAll();

    /**
     * @description 新增用户
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    Integer insert(UserDTO dto);

    /**
     * @description 根据id删除
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    Integer delete(Integer id);

    /**
     * @description 修改用户信息
     * @author LiuChangLan
     * @since 2020/11/5 17:01
     */
    Integer update(UserDTO userDTO);

    /**
     * @description 根据id查询
     * @author LiuChangLan
     * @since 2020/11/5 17:06
     */
    UserDTO selectById(Integer id);

}
