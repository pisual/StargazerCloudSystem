package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Result;

public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    private Result record;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<Result> recordArg){
        cache = cacheArg.get();
        record = recordArg.get();
    }

    @Override
    public void run() {

    }

}
