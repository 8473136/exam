package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.RoleDTO;
import com.guozhi.dto.RoleMenuDTO;
import com.guozhi.mapper.RoleMapper;
import com.guozhi.mapper.RoleMenuMapper;
import com.guozhi.service.RoleService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.PageVO;
import com.guozhi.vo.RoleMenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/8/19 18:37
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public PageInfo<RoleDTO> getRoleListByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<RoleDTO> roleDTOS = roleMapper.select(roleDTO);
        return new PageInfo<RoleDTO>(roleDTOS);
    }

    @Override
    public Integer insertRole(RoleDTO roleDTO) {
        roleDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return roleMapper.insertSelective(roleDTO);
    }

    @Override
    public Integer deleteRole(Integer id) {
        RoleDTO roleDTO = roleMapper.selectByPrimaryKey(id);
        roleDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        roleDTO.setUpdateTime(DateUtils.currentDateTime());
        roleDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
        return roleMapper.updateByPrimaryKeySelective(roleDTO);
    }

    @Override
    public RoleDTO getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateRole(RoleDTO roleDTO) {
        roleDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        roleDTO.setUpdateTime(DateUtils.currentDateTime());
        return roleMapper.updateByPrimaryKeySelective(roleDTO);
    }

    @Override
    @Transactional
    public Integer setRole(RoleMenuVO vo) {
        Integer count = 0;
        // 获取菜单id
        String[] split = vo.getMenuId().split(",");
        // 删除所有权限
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setRoleId(vo.getRoleId());
        roleMenuMapper.delete(roleMenuDTO);
        // 重新添加权限
        for (String s : split) {
            roleMenuDTO.setMenuId(Integer.valueOf(s));
            count += roleMenuMapper.insertSelective(roleMenuDTO);
        }
        return count;
    }

    @Override
    public List<RoleDTO> getAllRole() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<RoleDTO> roleDTOS = roleMapper.select(roleDTO);
        return roleDTOS;
    }
}
