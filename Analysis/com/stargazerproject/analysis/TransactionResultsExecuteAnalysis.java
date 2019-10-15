package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;

public interface TransactionResultsExecuteAnalysis {

    public Optional<TransactionResultsExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache);

}
