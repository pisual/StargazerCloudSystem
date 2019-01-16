package com.stargazerproject.annotation.description;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventStrategy {

    /**
     * 策略控制区
     * **/

    /**运行策略（单次，可以重复）**/
    public EventRunStrategy eventRunStrategy() default EventRunStrategy.Single;

    /**失败策略（幂等，回滚）**/
    public EventFailureStrategy eventFailureStrategy() default EventFailureStrategy.Idempotent;

    /**重试次数**/
    public int retryCount() default 0;
}
