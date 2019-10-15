package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsExecuteAnalysis;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventResultsExecuteAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultsExecuteAnalysisShell")
@Qualifier("eventResultsExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventResultsExecuteAnalysisShell implements EventResultsExecuteAnalysis, BaseCharacteristic<EventResultsExecuteAnalysis> {

    @Override
    public Optional<EventResultsExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new EventResultsExecuteAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<EventResultsExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }
}
