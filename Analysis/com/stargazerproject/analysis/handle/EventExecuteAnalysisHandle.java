package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResultState;

import java.util.concurrent.TimeUnit;

/**
 *  @name Event Execute Analysis Handle的接口
 *  @illustrate Event Execute Analysis Handle的接口
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public interface EventExecuteAnalysisHandle {

    /**
     * @name 事件（Event）具体运行方法
     * @illustrate 事件具体运行方法
     * **/
    public void run();

    public Optional<TimeUnit> waitTimeoutUnit();

    public Optional<Integer> waitTimeout();

    public Optional<EventResultState> resultState();

}
