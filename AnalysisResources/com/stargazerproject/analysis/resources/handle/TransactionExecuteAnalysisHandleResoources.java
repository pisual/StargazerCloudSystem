package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 *  @name Transaction Execute Analysis Handle 的具体实现
 *  @illustrate Transaction Execute Analysis Handle 的具体实现
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public class TransactionExecuteAnalysisHandleResoources implements TransactionExecuteAnalysisHandle {

    private EventExecuteAnalysis eventExecuteAnalysis;

    private Collection<Event> eventList;

    private TransactionResultsExecuteAnalysisHandle transactionResultsExecuteAnalysisHandle;

    /** @illustrate Transaction交互缓存接口 **/
    public Cache<String, String> interactionCache;

    public TransactionExecuteAnalysisHandleResoources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> transactionInteractionCacheArg, Optional<TransactionResultsExecuteAnalysisHandle> transactionResultsExecuteAnalysisHandleArg, Optional<EventExecuteAnalysis> eventExecuteAnalysisArg){
        eventList = eventListArg.get();
        eventExecuteAnalysis = eventExecuteAnalysisArg.get();
        interactionCache = transactionInteractionCacheArg.get();
        transactionResultsExecuteAnalysisHandle = transactionResultsExecuteAnalysisHandleArg.get();
    }

    /**
     * @name 启动Transaction序列事务方法
     * @illustrate 序列方法包括并行（乱序）非阻塞序列，标准顺序执行非阻塞序列，提供聚合跟功能的顺序执行非阻塞序列
     * **/
    @Override
    public void startTransaction() {
        eventList.forEach(event -> event.eventExecute(Optional.of(eventExecuteAnalysis)).get().run());
    }

    @Override
    public Optional<TimeUnit> waitTimeoutUnit() {
        return null;
    }

    @Override
    public Optional<Integer> waitTimeout() {
        return null;
    }

    @Override
    public Optional<TimeUnit> runTimeoutUnit() {
        return null;
    }

    @Override
    public Optional<Integer> runTimeout() {
        return null;
    }

}
