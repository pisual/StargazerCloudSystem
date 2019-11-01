package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventResultState;
import com.stargazerproject.transaction.TransactionResultState;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionResultAnalysisHandleResources implements TransactionResultAnalysisHandle {

    private EventResultAnalysis eventResultAnalysis;

    private Collection<Event> eventResultList;

    private Cache<String, String> parametersCache;

    private TransactionResultsResultAnalysisHandle transactionResultsResultAnalysisHandle;

    public TransactionResultAnalysisHandleResources(Optional<Collection<Event>> eventListArg, Optional<Cache<String, String>> parametersCacheArg, Optional<EventResultAnalysis> eventResultAnalysisArg, Optional<TransactionResultsResultAnalysisHandle> transactionResultsResultAnalysisHandleArg){
        eventResultList = eventListArg.get();
        parametersCache = parametersCacheArg.get();
        eventResultAnalysis = eventResultAnalysisArg.get();
        transactionResultsResultAnalysisHandle = transactionResultsResultAnalysisHandleArg.get();
    }

    @Override
    public Optional<TransactionResultState> getTheLastTransactionResultState() {

        List<EventResultState> eventResultStateList = eventResultList.parallelStream().map(event -> event.eventResult(Optional.of(eventResultAnalysis)).get().getTheLastEventResultState().get()).collect(Collectors.toList());

        for(EventResultState eventResultState : eventResultStateList){
            if(eventResultState == EventResultState.WAIT){
                return Optional.of(TransactionResultState.WAIT);
            }
            if(eventResultState == EventResultState.RUN){
                return Optional.of(TransactionResultState.Run);
            }
        }

        for(EventResultState eventResultState : eventResultStateList){
            if(eventResultState == EventResultState.FAULT){
                return Optional.of(TransactionResultState.FAULT);
            }
        }

        return Optional.of(TransactionResultState.SUCCESS);

    }

    @Override
    public Optional<String> getTheLastErrorMessage() {
        return null;
    }
}
