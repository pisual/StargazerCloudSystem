package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResultState;

public class EventResultAnalysisHandleResources implements EventResultAnalysisHandle {

    private Cache<String, String> cache;

    private EventResultsResultAnalysisHandle eventResultsResultAnalysisHandle;

    public EventResultAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<EventResultsResultAnalysisHandle> eventResultsResuleAnalysisHandleArg){
        cache = cacheArg.get();
        eventResultsResultAnalysisHandle = eventResultsResuleAnalysisHandleArg.get();
    }


    @Override
    public Optional<EventResultState> getTheLastEventResultState() {
        return eventResultsResultAnalysisHandle.getTheLastEventResultState();
    }

    @Override
    public Optional<String> getTheLastErrorMessage() {
        return eventResultsResultAnalysisHandle.getTheLastErrorMessage();
    }
}
