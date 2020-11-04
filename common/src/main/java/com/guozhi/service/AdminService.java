package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.AdminDTO;
import com.guozhi.vo.PageVO;

public interface AdminService {

    /**
     * @description 添加用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer addAdmin(AdminDTO adminDTO);

    /**
     * @description 删除用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer delAdmin(Integer id);

    /**
     * @description 修改用户
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    Integer updAdmin(AdminDTO adminDTO);

    /**
     * @description 获取用户分页列表
     * @author LiuChangLan
     * @since 2020/7/16 10:31
     */
    PageInfo<AdminDTO> getAdminListByPage(PageVO pageVO);

    /**
     * @description 根据id获取用户信息
     * @author LiuChangLan
     * @since 2020/7/16 10:38
     */
    AdminDTO getAdminById(Integer id);

    /**
     * @description 设置权限
     * @author LiuChangLan
     * @since 2020/8/24 17:45
     */
    Integer setAdminRole(AdminDTO adminDTO);

}
