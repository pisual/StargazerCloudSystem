package com.stargazerproject.annotation.description;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventCheck {

    /**
     * 先行检测
     * **/

    /**方法参数先行检测，将检测参数是否存在**/
    public boolean parametersBeforehandCheck() default false;
}
