package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.ResultState;

public class EventResultAnalysisHandleResources implements EventResultAnalysisHandle {

    private Cache<String, String> resultCache;

    private Cache<String, String> interactionCache;

    public EventResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg, Optional<Cache<String, String>> interactionCacheArg){
        resultCache = resultCacheArg.get();
        interactionCache = interactionCacheArg.get();
    }

    @Override
    public Optional<ResultState> resultState() {
        /**分析结果**/
        return conversionResultState("SUCCESS");
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
