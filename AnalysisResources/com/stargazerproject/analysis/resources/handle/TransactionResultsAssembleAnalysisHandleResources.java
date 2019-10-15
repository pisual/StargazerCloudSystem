package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class TransactionResultsAssembleAnalysisHandleResources implements TransactionResultsAssembleAnalysisHandle {

    private Cache<String, String> resultCache;

    public TransactionResultsAssembleAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

}
