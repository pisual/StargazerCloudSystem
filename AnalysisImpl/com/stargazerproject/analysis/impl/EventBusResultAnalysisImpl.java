package com.stargazerproject.analysis.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventBusResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventBusResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventBusResultAnalysisImpl")
@Qualifier("eventBusResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusResultAnalysisImpl extends BaseEventBusResultAnalysisImpl implements StanderCharacteristicShell<EventBusResultAnalysis>, BeforehandCharacteristicShell<EventBusResultAnalysis> {
	
	@Override
	public void initialize(Optional<EventBusResultAnalysis> eventBusResultAnalysisArg) {
		eventBusResultAnalysis = eventBusResultAnalysisArg.get();
	}

	@Override
	public void initialize(BaseCharacteristic<EventBusResultAnalysis> eventBusResultAnalysisArg) {
		eventBusResultAnalysis = eventBusResultAnalysisArg.characteristic().get();
	}
}
