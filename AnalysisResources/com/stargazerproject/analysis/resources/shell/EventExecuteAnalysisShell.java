package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventExecuteAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.EventState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventExecuteAnalysisShell")
@Qualifier("eventExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventExecuteAnalysisShell implements EventExecuteAnalysis, BaseCharacteristic<EventExecuteAnalysis> {

    @Override
    public Optional<EventExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> cacheArg, Optional<EventState> eventState, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg) {
        return Optional.of(new EventExecuteAnalysisHandleResources(cacheArg, eventState, eventResultsExecuteAnalysisHandleArg));
    }

    @Override
    public Optional<EventExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }
}
