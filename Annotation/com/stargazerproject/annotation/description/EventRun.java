package com.stargazerproject.annotation.description;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventRun {

    /**方法需要的参数列表**/
    public String CellsMethodNeedParameters() default "";

}
