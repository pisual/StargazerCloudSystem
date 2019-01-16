package com.stargazerproject.annotation.description;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventPreheat {


    /**
     * 流数据预热
     * **/

    /**是否需要数据预热**/
    public boolean dataPreheat() default false;
}
