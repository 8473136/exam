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
        InitialHomeRVO initialHomeRVO = new InitialHomeRVO();
        // 设置首页信息
        initialHomeRVO.getHomeInfo().put("title","首页");
        initialHomeRVO.getHomeInfo().put("href","page/home/welcome.html?t=1");
        // logo信息
        initialHomeRVO.getLogoInfo().put("title","考试系统");
        initialHomeRVO.getLogoInfo().put("image","images/logo.png");
        initialHomeRVO.getLogoInfo().put("href","");
        //菜单信息
        initialHomeRVO.setMenuInfo(authService.getLoginMenus());
        return initialHomeRVO;
    }

}
