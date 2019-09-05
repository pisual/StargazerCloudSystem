package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class TransactionResultRecordAnalysisHandleResources implements TransactionResultRecordAnalysisHandle {

    private Cache<String, String> resultCache;

    public TransactionResultRecordAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

    @Override
    public void errorMessage(Optional<Exception> exception) {
        resultCache.put(Optional.of("ErrorMessage"), Optional.of(exception.get().getMessage()));
    }
}
