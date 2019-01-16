package com.stargazerproject.annotation.description;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventTimeOut {

    /**
     * 时间控制区
     * **/

    /**等待的最长时间的单位**/
    public TimeUnit waitTimeoutUnit() default TimeUnit.MILLISECONDS;

    /**等待的最长时间阀值**/
    public int waitTimeout() default 500;

    /**运行的最长时间的单位**/
    public TimeUnit runTimeoutUnit() default TimeUnit.MILLISECONDS;

    /**运行的最长时间阀值**/
    public int runTimeout() default 500;


}
