package com.stargazerproject.annotation.description;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventConfiguration {
    public String name();

    public TimeUnit waitTimeoutUnit() default TimeUnit.MILLISECONDS; //等待时间单位
    public int waitTimeout() default 500;//等待的最长时间

    public TimeUnit runTimeoutUnit()default TimeUnit.MILLISECONDS;//运行时间单位
    public int runTimeout();//运行的最长时间

    public EventRunStrategy eventRunStrategy() default EventRunStrategy.Single;
    public EventFailureStrategy eventFailureStrategy() default EventFailureStrategy.Idempotent;

    public int retryCount() default 0;
}