package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultRecordAnalysis;
import com.stargazerproject.analysis.handle.EventResultRecordAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventResultRecordAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultRecordAnalysisShell")
@Qualifier("eventResultRecordAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventResultRecordAnalysisShell implements EventResultRecordAnalysis, BaseCharacteristic<EventResultRecordAnalysis> {

    @Override
    public Optional<EventResultRecordAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new EventResultRecordAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<EventResultRecordAnalysis> characteristic() {
        return Optional.of(this);
    }
}
