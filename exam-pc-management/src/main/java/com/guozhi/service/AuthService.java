package com.guozhi.service;

import com.guozhi.core.BusinessException;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.rvo.RoleMenuRVO;
import com.guozhi.vo.LoginVO;

import java.util.List;

public interface AuthService {

    LoginRVO login(LoginVO loginVO) throws BusinessException;

    String refreshToken(String accessToken);

    List<RoleMenuRVO> getLoginMenus();

}
