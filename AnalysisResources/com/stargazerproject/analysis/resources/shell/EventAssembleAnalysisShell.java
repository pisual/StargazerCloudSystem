package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.resources.handle.EventAssembleAnalysisHandleResources;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventAssembleAnalysisShell")
@Qualifier("eventAssembleAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventAssembleAnalysisShell implements EventAssembleAnalysis, BaseCharacteristic<EventAssembleAnalysis>{
	
	@Override
	public Optional<EventAssembleAnalysis> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public Optional<EventAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> interactionCache, Optional<EventResultsAssembleAnalysisHandle> eventResultsAssembleAnalysisHandleArg) {
		return Optional.of(new EventAssembleAnalysisHandleResources(interactionCache, eventResultsAssembleAnalysisHandleArg));
	}

}
