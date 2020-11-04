package com.guozhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.dto.UserDTO;
import com.guozhi.mapper.UserMapper;
import com.guozhi.service.UserService;
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
        dto.setIsDeleted(0);
        List<UserDTO> dtos = userMapper.select(dto);
        return new PageInfo<UserDTO>(dtos);
    }

    @Override
    public List<UserDTO> getUserAll() {
        UserDTO dto = new UserDTO();
        dto.setIsDeleted(0);
        List<UserDTO> dtos = userMapper.select(dto);
        return dtos;
    }
}
