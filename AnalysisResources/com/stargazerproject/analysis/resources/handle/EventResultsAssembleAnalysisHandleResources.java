package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class EventResultsAssembleAnalysisHandleResources implements EventResultsAssembleAnalysisHandle {

    private Cache<String, String> resultCache;

    public EventResultsAssembleAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

}