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
}
