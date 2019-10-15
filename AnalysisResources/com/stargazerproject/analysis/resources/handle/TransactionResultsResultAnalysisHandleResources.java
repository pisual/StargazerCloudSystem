package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class TransactionResultsResultAnalysisHandleResources implements TransactionResultsResultAnalysisHandle {

    private Cache<String, String> resultCache;

    public TransactionResultsResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }
    
}
