package com.stargazerproject.analysis.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultsAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultsAssembleAnalysisImpl")
@Qualifier("eventResultsAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultsAssembleAnalysisImpl extends BaseEventResultsAssembleAnalysisImpl implements StanderCharacteristicShell<EventResultsAssembleAnalysis>, BeforehandCharacteristicShell<EventResultsAssembleAnalysis> {

	@Override
	public void initialize(Optional<EventResultsAssembleAnalysis> eventResultsAssembleAnalysisArg) {
		eventResultsAssembleAnalysis = eventResultsAssembleAnalysisArg.get();
	}

	@Override
	@Autowired
	@Qualifier("eventResultsAssembleAnalysisShell")
	public void initialize(BaseCharacteristic<EventResultsAssembleAnalysis> eventResultsAssembleAnalysisArg) {
		eventResultsAssembleAnalysis = eventResultsAssembleAnalysisArg.characteristic().get();
	}
}
