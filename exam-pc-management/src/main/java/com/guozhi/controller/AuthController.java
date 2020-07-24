package com.guozhi.controller;

import com.guozhi.core.BusinessException;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.service.AuthService;
import com.guozhi.vo.LoginVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录授权接口
 * @author LiuchangLan
 * @date 2020/7/16 14:34
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("login")
    LoginRVO login(LoginVO loginVO) throws BusinessException {
        return authService.login(loginVO);
    }

    @PostMapping("refreshToken")
    String refreshToken(String accessToken){
        return authService.refreshToken(accessToken);
    }

}
