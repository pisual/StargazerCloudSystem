package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventResultAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

public class EventResultAnalysisShell implements EventResultAnalysis, BaseCharacteristic<EventResultAnalysis> {

    @Override
    public Optional<EventResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new EventResultAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<EventResultAnalysis> characteristic() {
        return Optional.of(this);
    }
}
