package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventResultsAssembleAnalysisImpl implements EventResultsAssembleAnalysis {

    protected EventResultsAssembleAnalysis eventResultsAssembleAnalysis;

    @Override
    public Optional<EventResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache){
        return eventResultsAssembleAnalysis.analysis(resultCache);
    }
}
