package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.TransactionResultState;

public class TransactionResultsResultAnalysisHandleResources implements TransactionResultsResultAnalysisHandle {

    private Cache<String, String> resultCache;

    public TransactionResultsResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

    @Override
    public Optional<TransactionResultState> getTheLastTransactionResultState() {
        return null;
    }

    @Override
    public Optional<String> getTheLastErrorMessage() {
        return null;
    }
}
