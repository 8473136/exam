package com.guozhi.config;

import com.guozhi.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * 设置拦截器
 * @author LiuchangLan
 * @date 2020/7/16 16:42
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${auth.Login.NotInterception}")
    private String notInterceptor;

    /**
     * @description 登录请求拦截器
     * @author LiuChangLan
     * @since 2020/6/30 11:34
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).excludePathPatterns(Arrays.asList(notInterceptor.split(",")));
    }
}
