package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.mapper.UserMapper;
import com.guozhi.service.UserService;
import com.guozhi.utils.DateUtils;
import com.guozhi.utils.MD5Utils;
import com.guozhi.utils.UUIDUtils;
import com.guozhi.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/16 10:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer addUser(UserDTO userDTO) {
        userDTO.setSalt(UUIDUtils.ramdomUUID());
        userDTO.setPassword(MD5Utils.encryption(userDTO.getPassword(), userDTO.getSalt()));
        return userMapper.insertSelective(userDTO);
    }

    @Override
    public Integer delUser(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIsDeleted(1);
        userDTO.setId(id);
        userDTO.setUpdateTime(DateUtils.currentDateTime());
        return userMapper.updateByPrimaryKeySelective(userDTO);
    }

    @Override
    public Integer updUser(UserDTO userDTO) {
        userDTO.setUpdateTime(DateUtils.currentDateTime());
        return userMapper.updateByPrimaryKeySelective(userDTO);
    }

    @Override
    public PageInfo<UserDTO> getUserListByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        UserDTO userDTO = new UserDTO();
        userDTO.setIsDeleted(0);
        List<UserDTO> userDTOS = userMapper.select(userDTO);
        return new PageInfo<>(userDTOS);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
