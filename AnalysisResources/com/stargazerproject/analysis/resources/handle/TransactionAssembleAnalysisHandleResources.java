package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public class TransactionAssembleAnalysisHandleResources implements TransactionAssembleAnalysisHandle {

    private Collection<Event> eventList;

    private Cache<String, String> interactionCache;

    private TransactionResultsAssembleAnalysisHandle transactionResultsAssembleAnalysisHandle;

    public TransactionAssembleAnalysisHandleResources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> interactionCacheArg, Optional<TransactionResultsAssembleAnalysisHandle> transactionResultsAssembleAnalysisHandleArg){
        eventList = eventListArg.get();
        interactionCache = interactionCacheArg.get();
        transactionResultsAssembleAnalysisHandle = transactionResultsAssembleAnalysisHandleArg.get();
    }

    @Override
    public TransactionAssembleAnalysisHandle injectTransactionParameter(Optional<String> Key, Optional<String> value) {
        interactionCache.put(Key, value);
        return this;
    }

    @Override
    public void assembleFromJson(Optional<String> jsonData) {
        System.out.println("等待书写assembleFromJson转换方法");
    }

    @Override
    public void addEvent(Optional<Event> eventArg) {
        eventList.add(eventArg.get());
    }
}
