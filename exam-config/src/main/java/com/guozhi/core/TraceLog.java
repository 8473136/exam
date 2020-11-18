package com.guozhi.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 * @author LiuchangLan
 * @date 2020/7/23 18:49
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface TraceLog {

    /**
     * @description 业务
     * @author LiuChangLan
     * @since 2020/7/23 18:49
     */
    public String business() default "";

    /**
     * @description 模块
     * @author LiuChangLan
     * @since 2020/7/23 18:49
     */
    public String module() default "";

}
