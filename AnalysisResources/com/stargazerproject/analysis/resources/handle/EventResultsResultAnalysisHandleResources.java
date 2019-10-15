package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class EventResultsResultAnalysisHandleResources implements EventResultsResultAnalysisHandle {

    private Cache<String, String> resultCache;

//    private Cache<String, String> parametersCache; , Optional<Cache<String, String>> parametersCacheArg

    public EventResultsResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
//        parametersCache = parametersCacheArg.get();
    }

}