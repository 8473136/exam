package com.guozhi.mapper;

import com.guozhi.dto.RoleMenuDTO;
import com.guozhi.rvo.RoleMenuRVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/8/21 14:15
 */
public interface RoleMenuMapper extends Mapper<RoleMenuDTO> {

    List<RoleMenuRVO> getRoleMenu(Integer roleId);

}
