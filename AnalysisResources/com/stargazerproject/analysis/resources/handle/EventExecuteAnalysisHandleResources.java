package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.EventResultState;
import com.stargazerproject.transaction.EventState;
import com.stargazerproject.transaction.date.EventDate;

import java.util.concurrent.TimeUnit;

/**
 *  @name Event Execute Analysis Handle的具体实现
 *  @illustrate Event Execute Analysis Handle的具体实现
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    private EventResultsExecuteAnalysisHandle eventResultsExecuteAnalysisHandle;

    private EventState eventState;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<EventState> eventStateArg, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg){
        cache = cacheArg.get();
        eventResultsExecuteAnalysisHandle = eventResultsExecuteAnalysisHandleArg.get();
        eventState = eventStateArg.get();
    }

    /**
     * @name 事件（Event）具体运行方法
     * @illustrate 事件具体运行方法
     * **/
    @Override
    public void run() {
        eventState = EventState.RUN;
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
        cellsTransaction.method(Optional.of(cache), Optional.of(eventResultsExecuteAnalysisHandle));
        eventState = EventState.COMPLETE;
    }

    /**
     * @name 从参数缓存中获取Method（方法）名称
     * @illustrate 从参数缓存中获取Method（方法）名称
     * **/
    private Optional<String> method(){
        return cache.get(Optional.of(EventDate.Method.toString()));
    }

    @Override
    public Optional<TimeUnit> waitTimeoutUnit(){
        String waitTimeoutUnit = cache.get(Optional.of(EventDate.WaitTimeoutUnit.toString())).get();
        return conversionTimeUnit(waitTimeoutUnit);
    }

    @Override
    public Optional<Integer> waitTimeout(){
        String waitTimeout = cache.get(Optional.of(EventDate.WaitTimeout.toString())).get();
        return Optional.of(Integer.parseInt(waitTimeout));
    }

    @Override
    public Optional<TimeUnit> runTimeoutUnit() {
        String waitTimeoutUnit = cache.get(Optional.of(EventDate.RunTimeoutUnit.toString())).get();
        return conversionTimeUnit(waitTimeoutUnit);
    }

    @Override
    public Optional<Integer> runTimeout() {
        String waitTimeout = cache.get(Optional.of(EventDate.RunTimeout.toString())).get();
        return Optional.of(Integer.parseInt(waitTimeout));
    }

    private Optional<TimeUnit> conversionTimeUnit(String waitTimeoutUnit){
        switch (waitTimeoutUnit){
            case "NANOSECONDS":
                return Optional.of(TimeUnit.NANOSECONDS);
            case "MICROSECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "MILLISECONDS":
                return Optional.of(TimeUnit.MILLISECONDS);
            case "SECONDS":
                return Optional.of(TimeUnit.SECONDS);
            case "HOURS":
                return Optional.of(TimeUnit.HOURS);
            case "DAYS":
                return Optional.of(TimeUnit.DAYS);
            default:
                throw new IllegalArgumentException("waitTimeoutUnit Error");
        }
    }

    private Optional<EventResultState> conversionResultState(String result){
        EventResultState resultState;
        switch (result){
            case "SUCCESS":
                resultState = EventResultState.SUCCESS;
                break;
            case "FAULT":
                resultState = EventResultState.FAULT;
                break;
            case "WAIT":
                resultState = EventResultState.WAIT;
                break;
            default:
                throw new IllegalArgumentException("EventResultState Error , EventResultState : " + result);
        }
        return Optional.of(resultState);
    }


}
