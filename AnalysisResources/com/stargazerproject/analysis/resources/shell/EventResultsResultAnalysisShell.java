package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventResultsResultAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultsResultAnalysisShell")
@Qualifier("eventResultsResultAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventResultsResultAnalysisShell implements EventResultsResultAnalysis, BaseCharacteristic<EventResultsResultAnalysis> {

    @Override
    public Optional<EventResultsResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new EventResultsResultAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<EventResultsResultAnalysis> characteristic() {
        return Optional.of(this);
    }
}
