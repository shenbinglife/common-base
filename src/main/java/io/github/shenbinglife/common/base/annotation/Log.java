package io.github.shenbinglife.common.base.annotation;

import java.lang.annotation.*;

import io.github.shenbinglife.common.base.domain.LogLevel;

/**
 * 日志注解
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Log {

    /**
     * 日志文本
     */
    String value();

    /**
     * 是否记录方法参数
     */
    boolean logParams() default false;

    /**
     * 是否记录方法结果
     */
    boolean logResult() default false;

    /**
     * 当遇到指定异常时，执行日志拦截的操作
     */
    Class<? extends Exception>[] expect() default {};

    /**
     * 被拦截的操作等级
     */
    LogLevel level() default LogLevel.NORMAL;

}
