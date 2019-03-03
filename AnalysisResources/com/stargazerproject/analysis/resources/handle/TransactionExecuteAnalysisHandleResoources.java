package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class TransactionExecuteAnalysisHandleResoources implements TransactionExecuteAnalysisHandle {

    /** @illustrate 获取Log(日志)接口 **/
    @Autowired
    @Qualifier("logRecord")
    private LogMethod logMethod;

    @Autowired
    @Qualifier("eventQueue")
    private Queue<Event> eventQueue;

//    @Autowired
//    @Qualifier("sequenceTransactionQueue")
//    private Queue<Event> sequenceTransactionQueue;

    /** @illustrate Transaction交互缓存接口 **/
    public Cache<String, String> interactionCache;

    private Collection<Event> eventList;

    public TransactionExecuteAnalysisHandleResoources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> transactionInteractionCacheArg){
        eventList = eventListArg.get();
        interactionCache = transactionInteractionCacheArg.get();
    }

    @Override
    public void startTransaction() {

    }

    private void startParallelTransaction(){
        eventList.forEach(event -> eventQueue.producer(Optional.of(event)));
    }

    private void startSequenceTransaction(){

    }

    private String checkTransactionRunWay(){
        return interactionCache.get(Optional.of("RunWay")).get();
    }

}
