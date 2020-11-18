package com.guozhi.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.UserDTO;
import com.guozhi.mapper.UserMapper;
import com.guozhi.service.UserService;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/11/4 10:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<UserDTO> getUserListByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        UserDTO dto = new UserDTO();
        dto.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<UserDTO> dtos = userMapper.select(dto);
        return new PageInfo<UserDTO>(dtos);
    }

    @Override
    public List<UserDTO> getUserAll() {
        UserDTO dto = new UserDTO();
        dto.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<UserDTO> dtos = userMapper.select(dto);
        return dtos;
    }


    @Override
    public Integer insert(UserDTO dto) {
        dto.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return userMapper.insertSelective(dto);
    }

    @Override
    public Integer delete(Integer id) {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setIsDeleted(DataGlobalVariable.IS_DELETE);
        dto.setUpdateTime(DateUtil.now());
        dto.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return userMapper.updateByPrimaryKeySelective(dto);
    }

    @Override
    public Integer update(UserDTO userDTO) {
        userDTO.setUpdateTime(DateUtil.now());
        userDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return userMapper.updateByPrimaryKeySelective(userDTO);
    }

    @Override
    public UserDTO selectById(Integer id) {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        return userMapper.selectOne(dto);
    }
}
