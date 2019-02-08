package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultState;

public class EventResultAnalysisHandleResources implements EventResultAnalysisHandle {

    private Cache<String, String> resultCache;

    public EventResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

    @Override
    public Optional<ResultState> resultState() {
        String result = resultCache.get(Optional.of("ResultState")).get();
        return conversionResultState(result);
    }

    private Optional<ResultState> conversionResultState(String result){
        ResultState resultState;
        switch (result){
            case "SUCCESS":
                resultState = ResultState.SUCCESS;
            break;
            case "FAULT":
                resultState = ResultState.FAULT;
                break;
            case "WAIT":
                resultState = ResultState.WAIT;
                break;
            default:
                throw new NullPointerException("ResultState Null");
        }
        return Optional.of(resultState);
    }

}
