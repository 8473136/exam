package com.guozhi.service;

import com.guozhi.core.BusinessException;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.vo.LoginVO;

public interface AuthService {

    LoginRVO login(LoginVO loginVO) throws BusinessException;

}
