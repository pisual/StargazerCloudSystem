package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventResultsAssembleAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultsAssembleAnalysisShell")
@Qualifier("eventResultsAssembleAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventResultsAssembleAnalysisShell implements EventResultsAssembleAnalysis, BaseCharacteristic<EventResultsAssembleAnalysis> {

    @Override
    public Optional<EventResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new EventResultsAssembleAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<EventResultsAssembleAnalysis> characteristic() {
        return Optional.of(this);
    }
}
