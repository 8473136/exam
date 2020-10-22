package com.guozhi.service.impl;

import com.guozhi.dto.MenuDTO;
import com.guozhi.mapper.MenuMapper;
import com.guozhi.mapper.RoleMenuMapper;
import com.guozhi.rvo.RoleMenuRVO;
import com.guozhi.service.MenuService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单业务层
 * @author LiuchangLan
 * @date 2020/8/19 15:18
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuDTO> getAllMenus() {
        return menuMapper.getMenusOrder();
    }

    @Override
    public Integer addMenu(MenuDTO menuDTO) {
        menuDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return menuMapper.insertSelective(menuDTO);
    }

    @Override
    public Integer deleteMenu(Integer id) {
        MenuDTO menuDTO = menuMapper.selectByPrimaryKey(id);
        menuDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        menuDTO.setUpdateTime(DateUtils.currentDateTime());
        menuDTO.setIsDeleted(1);
        return menuMapper.updateByPrimaryKeySelective(menuDTO);
    }

    @Override
    public MenuDTO getMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateMenu(MenuDTO menuDTO) {
        menuDTO.setUpdateTime(DateUtils.currentDateTime());
        menuDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return menuMapper.updateByPrimaryKeySelective(menuDTO);
    }

    @Override
    public List<RoleMenuRVO> getMenuTree(Integer roleId) {
//        // 获取所有菜单
//        List<MenuDTO> menusOrder = menuMapper.getMenusOrder();
//        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
//        roleMenuDTO.setRoleId(roleId);
//        // 获取所有权限
//        List<RoleMenuDTO> roleMenuDTOS = roleMenuMapper.select(roleMenuDTO);
//        // 返回结果
//        List<RoleMenuRVO> roleMenuRVOS = new ArrayList<>();
//        // 循环封装
//        for (MenuDTO menuDTO : menusOrder) {
//            RoleMenuRVO roleMenuRVO = new RoleMenuRVO();
//            BeanUtil.copyProperties(menuDTO,roleMenuRVO);
//            for (RoleMenuDTO dto : roleMenuDTOS) {
//                boolean flag = false;
//                // 判断是否有子元素
//                for (MenuDTO menuDTO1 : menusOrder) {
//                    if (menuDTO1.getParentId().equals(menuDTO.getId())){
//                        flag = true;
//                        break;
//                    }
//                }
//                // 有权限 并且没有子元素的默认选中
//                if(dto.getMenuId().equals(roleMenuRVO.getId()) && !flag){
//                    roleMenuRVO.setChecked(true);
//                    break;
//                }
//            }
//            roleMenuRVOS.add(roleMenuRVO);
//        }
//        List<RoleMenuRVO> list = TreeUtils.buildTree(roleMenuRVOS, -1);
//        return list;
        return null;
    }
}
