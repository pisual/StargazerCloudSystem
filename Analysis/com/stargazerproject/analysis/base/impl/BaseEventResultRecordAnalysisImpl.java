package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultRecordAnalysis;
import com.stargazerproject.analysis.handle.EventResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventResultRecordAnalysisImpl implements EventResultRecordAnalysis {

    protected EventResultRecordAnalysis eventResultRecordAnalysis;

    @Override
    public Optional<EventResultRecordAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return eventResultRecordAnalysis.analysis(resultCache);
    }
}
