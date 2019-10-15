package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class TransactionResultsExecuteAnalysisHandleResources implements TransactionResultsExecuteAnalysisHandle {

    private Cache<String, String> resultCache;

    public TransactionResultsExecuteAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }
    
}
