package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public interface TransactionResultAnalysis{

    /** @illustrate 结果分析器 **/
    public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Cache<String, String>> parametersCache, Optional<Collection<Event>> eventList);
	
}
