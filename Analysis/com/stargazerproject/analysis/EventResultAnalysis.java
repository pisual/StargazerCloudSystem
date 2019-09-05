package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public interface EventResultAnalysis{

    /** @illustrate 结果分析器 **/
    public Optional<EventResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Cache<String, String>> parametersCache);
}
