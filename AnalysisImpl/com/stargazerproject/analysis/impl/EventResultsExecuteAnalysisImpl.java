package com.stargazerproject.analysis.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultsExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultsExecuteAnalysisImpl")
@Qualifier("eventResultsExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultsExecuteAnalysisImpl extends BaseEventResultsExecuteAnalysisImpl implements StanderCharacteristicShell<EventResultsExecuteAnalysis>, BeforehandCharacteristicShell<EventResultsExecuteAnalysis> {

	@Override
	public void initialize(Optional<EventResultsExecuteAnalysis> eventResultsExecuteAnalysisArg) {
		eventResultsExecuteAnalysis = eventResultsExecuteAnalysisArg.get();
	}

	@Override
	@Autowired
	@Qualifier("eventResultsExecuteAnalysisShell")
	public void initialize(BaseCharacteristic<EventResultsExecuteAnalysis> eventResultsExecuteAnalysisArg) {
		eventResultsExecuteAnalysis = eventResultsExecuteAnalysisArg.characteristic().get();
	}
}
