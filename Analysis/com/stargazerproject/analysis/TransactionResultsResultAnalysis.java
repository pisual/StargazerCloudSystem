package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public interface TransactionResultsResultAnalysis {

    public Optional<TransactionResultsResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache);

}
