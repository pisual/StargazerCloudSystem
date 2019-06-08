package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.ResultState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class TransactionResultAnalysisHandleResources implements TransactionResultAnalysisHandle {

    @Autowired
    @Qualifier("eventResultAnalysisImpl")
    private EventResultAnalysis eventResultAnalysis;

    private Collection<Event> eventResultList;

    private Cache<String, String> resultCache;

    private Cache<String, String> resultInteractionCache;

    public TransactionResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg, Optional<Collection<Event>> interactionCacheArg, Optional<Cache<String, String>> resultInteractionCacheArg){
        resultCache = resultCacheArg.get();
        eventResultList = interactionCacheArg.get();
        resultInteractionCache = resultInteractionCacheArg.get();
    }

    @Override
    public Optional<ResultState> resultState() {
        //TODO 分析结果状态 未完成
        for (EventResult eventResult : eventResultList) {
            eventResult.eventResult(Optional.of(eventResultAnalysis));
        }
        return null;
    }
}
