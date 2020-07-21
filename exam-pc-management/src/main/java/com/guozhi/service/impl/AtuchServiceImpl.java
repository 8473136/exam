package com.guozhi.service.impl;

import com.guozhi.common.DataGlobalVariable;
import com.guozhi.common.JwtPayload;
import com.guozhi.core.BusinessException;
import com.guozhi.dto.UserDTO;
import com.guozhi.exception.GlobalException;
import com.guozhi.mapper.UserMapper;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.service.AuthService;
import com.guozhi.utils.JwtUtils;
import com.guozhi.utils.MD5Utils;
import com.guozhi.utils.UUIDUtils;
import com.guozhi.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LiuchangLan
 * @date 2020/7/16 14:34
 */
@Service
public class AtuchServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public LoginRVO login(LoginVO loginVO) throws BusinessException {
        UserDTO userDTO = new UserDTO();
        userDTO.setIsDeleted(0);
        userDTO.setAccount(loginVO.getAccount());
        UserDTO loginUserInfo = userMapper.selectOne(userDTO);

        // 用户不存在
        if (loginUserInfo == null) {
            throw new BusinessException(GlobalException.ACCOUNT_NOT_EXISTS);
        }

        // 判断密码
        if (!MD5Utils.encryption(loginVO.getPassword(), loginUserInfo.getSalt()).equals(loginUserInfo.getPassword())) {
            throw new BusinessException(GlobalException.ACCOUNT_OR_PASSWORD_INCORRECT);
        }

        // 判断用户状态
        if (DataGlobalVariable.USER_STATUS_DISABLE.equals(loginUserInfo.getUserStatus())) {
            throw new BusinessException(GlobalException.ACCOUNT_DISABLE);
        }

        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setAccount(loginUserInfo.getAccount());
        jwtPayload.setId(loginUserInfo.getId());
        jwtPayload.setDeptId(loginUserInfo.getDeptId());
        jwtPayload.setNickName(loginUserInfo.getNickName());

        String accessToken = JwtUtils.createToken(jwtPayload, 2 * 60 * 1000L);
        String refreshToken = UUIDUtils.ramdomUUID();

        return new LoginRVO(accessToken,refreshToken,System.currentTimeMillis());
    }

    @Override
    public String refreshToken(String accessToken) {
        JwtPayload jwtPayload = JwtUtils.decrypt(accessToken);
        return JwtUtils.createToken(jwtPayload, 2 * 60 * 1000L);
    }
}
