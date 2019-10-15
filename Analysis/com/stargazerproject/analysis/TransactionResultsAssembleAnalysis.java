package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public interface TransactionResultsAssembleAnalysis {

    public Optional<TransactionResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCacheArg);

}
