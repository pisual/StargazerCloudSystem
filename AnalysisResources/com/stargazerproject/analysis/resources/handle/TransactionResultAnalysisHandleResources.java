package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class TransactionResultAnalysisHandleResources implements TransactionResultAnalysisHandle {

    @Autowired
    @Qualifier("eventResultAnalysisImpl")
    private EventResultAnalysis eventResultAnalysis;

    private Collection<Event> eventResultList;

    private Cache<String, String> parametersCache;

    private TransactionResultsResultAnalysisHandle transactionResultsResultAnalysisHandle;

    public TransactionResultAnalysisHandleResources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> parametersCacheArg, Optional<TransactionResultsResultAnalysisHandle> transactionResultsResultAnalysisHandleArg){
        eventResultList = eventListArg.get();
        parametersCache = parametersCacheArg.get();
        transactionResultsResultAnalysisHandle = transactionResultsResultAnalysisHandleArg.get();
    }

}
