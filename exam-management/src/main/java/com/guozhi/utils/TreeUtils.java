package com.guozhi.utils;

import com.guozhi.rvo.RoleMenuRVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/8/25 9:37
 */
public class TreeUtils {

    public static List<RoleMenuRVO> buildTreeHome(List<RoleMenuRVO> list, int parentId) {
        List<RoleMenuRVO> menus = new ArrayList<RoleMenuRVO>();
        for (RoleMenuRVO roleMenuRVO : list) {
            int id = roleMenuRVO.getId();
            int pid = roleMenuRVO.getParentId();
            if (parentId == pid) {
                List<RoleMenuRVO> menuLists = buildTreeHome(list, id);
                roleMenuRVO.setChild(menuLists);
                menus.add(roleMenuRVO);
            }
        }
        return menus;
    }

    public static List<RoleMenuRVO> buildTree(List<RoleMenuRVO> list, int parentId) {
        List<RoleMenuRVO> menus = new ArrayList<RoleMenuRVO>();
        for (RoleMenuRVO roleMenuRVO : list) {
            int id = roleMenuRVO.getId();
            int pid = roleMenuRVO.getParentId();
            if (parentId == pid) {
                List<RoleMenuRVO> menuLists = buildTree(list, id);
                roleMenuRVO.setChildren(menuLists);
                menus.add(roleMenuRVO);
            }
        }
        return menus;
    }
}
