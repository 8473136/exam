package com.guozhi.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.dto.AdminDTO;
import com.guozhi.mapper.AdminMapper;
import com.guozhi.service.AdminService;
import com.guozhi.utils.JwtUtils;
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
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Integer addAdmin(AdminDTO adminDTO) {
        adminDTO.setSalt(UUIDUtils.ramdomUUID());
        adminDTO.setPassword(MD5Utils.encryption(adminDTO.getPassword(), adminDTO.getSalt()));
        adminDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return adminMapper.insertSelective(adminDTO);
    }

    @Override
    public Integer delAdmin(Integer id) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setIsDeleted(DataGlobalVariable.IS_DELETE);
        adminDTO.setId(id);
        adminDTO.setUpdateTime(DateUtil.now());
        adminDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        return adminMapper.updateByPrimaryKeySelective(adminDTO);
    }

    @Override
    public Integer updAdmin(AdminDTO adminDTO) {
        adminDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        adminDTO.setUpdateTime(DateUtil.now());
        return adminMapper.updateByPrimaryKeySelective(adminDTO);
    }

    @Override
    public PageInfo<AdminDTO> getAdminListByPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setIsDeleted(DataGlobalVariable.IS_NOT_DELETE);
        List<AdminDTO> adminDTOS = adminMapper.select(adminDTO);
        return new PageInfo<>(adminDTOS);
    }

    @Override
    public AdminDTO getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer setAdminRole(AdminDTO adminDTO) {
        adminDTO.setUpdatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        adminDTO.setUpdateTime(DateUtil.now());
        return adminMapper.updateByPrimaryKeySelective(adminDTO);
    }
}
