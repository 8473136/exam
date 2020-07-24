package com.guozhi.aop;

import com.alibaba.fastjson.JSONObject;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.core.TraceLog;
import com.guozhi.dto.LogDTO;
import com.guozhi.mapper.LogMapper;
import com.guozhi.utils.JwtUtils;
import com.guozhi.utils.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuchangLan
 * @date 2020/7/23 14:48
 */
@Aspect
@Component
public class WebLogAspect {

    /**
     * 切入点
     */
    public static final String POINTCUT = "execution(* com.guozhi.controller..*Controller.*(..))";

    @Resource
    private LogMapper logMapper;

    @Before(POINTCUT)
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        LogDTO logDTO = loadLogDto(joinPoint);
        if (logDTO == null) {
            return;
        }
        logMapper.insertSelective(logDTO);
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "e")
    @Transactional
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        LogDTO logDTO = loadLogDto(joinPoint);
        // 删除已经纪录的日志
        logMapper.delete(logDTO);
        logDTO.setLogType(DataGlobalVariable.EXCEPTION_LOG_DICT_ID);
        logDTO.setLogContent(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
        // 新增错误日志
        logMapper.insertSelective(logDTO);
    }

    /**
     * * 转换异常信息为字符串
     * *
     * * @param exceptionName    异常名称
     * * @param exceptionMessage 异常信息
     * * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

    /**
     * @description 根据连接点载体日志dto
     * @author LiuChangLan
     * @since 2020/7/23 15:55
     */
    public LogDTO loadLogDto(JoinPoint joinPoint) {
        // 获取自定义注解
        TraceLog traceLog = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(TraceLog.class);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 类名
        String className = joinPoint.getTarget().getClass().getName();
        // 授权类跳过
        if (className == DataGlobalVariable.AUTH_CLASS_PATH) {
            return null;
        }
        // 构造日志对象
        LogDTO logDTO = new LogDTO();
        logDTO.setExecMethod(className + "." + joinPoint.getSignature().getName());
        logDTO.setParams(JSONObject.toJSONString(joinPoint.getArgs()));
        logDTO.setUserId(JwtUtils.getCurrentUserJwtPayload().getId());
        logDTO.setCreatedBy(JwtUtils.getCurrentUserJwtPayload().getId());
        logDTO.setLogType(DataGlobalVariable.EXEC_LOG_DICT_ID);
        logDTO.setExecIp(RequestUtils.getIpAddress(request));
        logDTO.setModule(traceLog.module());
        logDTO.setBusiness(traceLog.business());
        return logDTO;
    }

}
