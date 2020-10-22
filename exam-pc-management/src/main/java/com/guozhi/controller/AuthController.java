package com.guozhi.controller;

import com.guozhi.core.BusinessException;
import com.guozhi.rvo.InitialHomeRVO;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.service.AuthService;
import com.guozhi.utils.JwtUtils;
import com.guozhi.vo.LoginVO;
import org.springframework.web.bind.annotation.*;

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
//    @TraceLog(module = "登录授权模块",business = "用户登录")
    public LoginRVO login(@RequestBody LoginVO loginVO) throws BusinessException {
        return authService.login(loginVO);
    }

    @PostMapping("refreshToken")
    public String refreshToken(String accessToken){
        return authService.refreshToken(accessToken);
    }

    @GetMapping("getLoginUserName")
    public String getLoginUserName(){
        return JwtUtils.getCurrentUserJwtPayload().getNickName();
    }

    @GetMapping("getLoginMenus")
    public InitialHomeRVO getLoginMenus(){
        return authService.getLoginMenus();
    }

}
