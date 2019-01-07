package com.stargazerproject.annotation.description;

public @interface EventConfiguration {
    public String name();

    public EventTimeUnit waitTimeoutUnit();//等待时间单位
    public int waitTimeout();//等待的最长时间

    public EventTimeUnit runTimeoutUnit();//运行时间单位
    public int runTimeout();//运行的最长时间

    public EventRunStrategy eventRunStrategy();
    public EventFailureStrategy eventFailureStrategy();
}
