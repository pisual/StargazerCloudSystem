package com.stargazerproject.annotation.description;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EventCheck
@EventPreheat
@EventRun
@EventStrategy
@EventTimeOut
public @interface Event{

}
