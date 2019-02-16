package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.EventExecuteAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventExecuteAnalysisShell")
@Qualifier("eventExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventExecuteAnalysisShell implements EventExecuteAnalysis, BaseCharacteristic<EventExecuteAnalysis> {

    @Override
    public Optional<EventExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> cacheArg) {
        return Optional.of(new EventExecuteAnalysisHandleResources(cacheArg));
    }

    @Override
    public Optional<EventExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }
}
