package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsExecuteAnalysis;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventResultsExecuteAnalysisImpl implements EventResultsExecuteAnalysis {

    protected EventResultsExecuteAnalysis eventResultsExecuteAnalysis;

    @Override
    public Optional<EventResultsExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return eventResultsExecuteAnalysis.analysis(resultCache);
    }
}
