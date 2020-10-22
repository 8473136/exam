package com.guozhi.service;

import com.github.pagehelper.PageInfo;
import com.guozhi.dto.RoleDTO;
import com.guozhi.vo.PageVO;

import java.util.List;

/**
 * @author LiuChangLan
 * @since 2020/8/19 18:34
 */
public interface RoleService {

    /**
     * @description 查询权限分页列表
     * @author LiuChangLan
     * @since 2020/8/19 18:37
     */
    PageInfo<RoleDTO> getRoleListByPage(PageVO pageVO);

    /**
     * @description 新增权限
     * @author LiuChangLan
     * @since 2020/8/19 18:37
     */
    Integer insertRole(RoleDTO roleDTO);

    /**
     * @description 删除权限
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    Integer deleteRole(Integer id);

    /**
     * @description 根据权限id获取权限详细信息
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    RoleDTO getRoleById(Integer id);

    /**
     * @description 修改权限
     * @author LiuChangLan
     * @since 2020/8/19 18:38
     */
    Integer updateRole(RoleDTO roleDTO);

    /**
     * @description 设置权限
     * @author LiuChangLan
     * @since 2020/8/24 16:40
     */
    Integer setRole(Integer roleId,String menuIds);

    /**
     * @description 获取所有权限列表
     * @author LiuChangLan
     * @since 2020/8/24 17:32
     */
    List<RoleDTO> getAllRole();

}
