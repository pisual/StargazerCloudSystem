package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class TransactionAssembleAnalysisHandleResources implements TransactionAssembleAnalysisHandle {

    private Collection<Event> eventList;

    private Cache<String, String> interactionCache;

    public TransactionAssembleAnalysisHandleResources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> interactionCacheArg){
        eventList = eventListArg.get();
    }

    @Override
    public void assembleFromJson(Optional<String> jsonData) {
        System.out.println("等待书写assembleFromJson转换方法");
    }

    @Override
    public void addEvent(Optional<Event> eventArg) {
        eventList.add(eventArg.get());
    }

    @Override
    public Optional<Integer> getTransactionTimeOut() {
        return null;
    }

    @Override
    public Optional<TimeUnit> getTransactionTimeOutTimeUnit() {
        return null;
    }
}
