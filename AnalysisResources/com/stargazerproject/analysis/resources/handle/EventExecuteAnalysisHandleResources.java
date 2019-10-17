package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.EventResultState;

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

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg){
        cache = cacheArg.get();
        eventResultsExecuteAnalysisHandle = eventResultsExecuteAnalysisHandleArg.get();
    }

    /**
     * @name 事件（Event）具体运行方法
     * @illustrate 事件具体运行方法
     * **/
    @Override
    public void run() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
        cellsTransaction.method(Optional.of(cache), Optional.of(eventResultsExecuteAnalysisHandle));
    }

    /**
     * @name 从参数缓存中获取Method（方法）名称
     * @illustrate 从参数缓存中获取Method（方法）名称
     * **/
    private Optional<String> method(){
        return cache.get(Optional.of("Method"));
    }


    @Override
    public Optional<EventResultState> resultState() {
        String resultState = cache.get(Optional.of("EventResultState")).get();
        return conversionResultState(resultState);
    }

    @Override
    public Optional<TimeUnit> waitTimeoutUnit(){
        String waitTimeoutUnit = cache.get(Optional.of("waitTimeoutUnit")).get();
        switch (waitTimeoutUnit){
            case "MICROSECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "MILLISECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "SECONDS":
                return Optional.of(TimeUnit.SECONDS);
            default:
                throw new NullPointerException("waitTimeoutUnit Error");
        }
    }

    @Override
    public Optional<Integer> waitTimeout(){
        String waitTimeout = cache.get(Optional.of("waitTimeout")).get();
        return Optional.of(Integer.parseInt(waitTimeout));
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
                throw new NullPointerException("EventResultState Error , EventResultState : " + result);
        }
        return Optional.of(resultState);
    }


}
