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
        //TODO 分析结果状态
        return null;
    }

}
