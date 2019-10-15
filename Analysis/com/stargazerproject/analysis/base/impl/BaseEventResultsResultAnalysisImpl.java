package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventResultsResultAnalysisImpl implements EventResultsResultAnalysis {

    protected EventResultsResultAnalysis eventResultsResultAnalysis;

    @Override
    public Optional<EventResultsResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache){
        return eventResultsResultAnalysis.analysis(resultCache);
    }
}
