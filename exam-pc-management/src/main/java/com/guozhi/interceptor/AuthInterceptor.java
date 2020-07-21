package com.guozhi.interceptor;

import com.guozhi.common.DataGlobalVariable;
import com.guozhi.core.BusinessException;
import com.guozhi.exception.GlobalException;
import com.guozhi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiuchangLan
 * @date 2020/6/30 11:02
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader(DataGlobalVariable.HEADER_ACCCESS_TOKEN);
        if (accessToken == null || "null".equals(accessToken) || "".equals(accessToken)){
            // 请求头没有token
            throw new BusinessException(GlobalException.NO_LOGIN);
        }else {
            if (JwtUtils.verifier(accessToken)){
                //验证成功
                log.info("用户已登录");
                return true;
            }else {
                //验证失败
                log.debug("用户未登录");
                throw new BusinessException(GlobalException.NO_LOGIN);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
