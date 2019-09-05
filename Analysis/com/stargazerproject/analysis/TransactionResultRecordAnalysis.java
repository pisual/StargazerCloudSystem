package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;

public interface TransactionResultRecordAnalysis{

    public Optional<TransactionResultRecordAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache);

}
