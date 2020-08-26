package com.guozhi.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理返回结果
 * @author LiuchangLan
 * @date 2020/6/27 22:05
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断支持的类型，因为data可能是任何类型，这里就不判断统一放过
        //如果你想对执行的返回体进行操作，可将上方的Object换成你自己的类型
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        log.info("请求返回数据类型class={}", body.getClass().getName());
        Result result = null;
        if (body instanceof Result){
            result = (Result) body;
        }else {
            result = new Result();
            result.setCode(ResultStatusCode.SUCCESS.getCode());
            result.setMsg(ResultStatusCode.SUCCESS.getMsg());
            result.setData(body == null ? "" : body);
        }
        //处理返回值是String的情况
        if (body instanceof String || body == null) {
            return JSON.toJSONString(result);
        }
        return result;
    }
}
