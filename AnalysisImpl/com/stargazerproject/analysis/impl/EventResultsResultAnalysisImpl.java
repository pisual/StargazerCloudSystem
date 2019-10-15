package com.stargazerproject.analysis.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultsResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultResultAnalysisImpl")
@Qualifier("eventResultRecordAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultsResultAnalysisImpl extends BaseEventResultsResultAnalysisImpl implements StanderCharacteristicShell<EventResultsResultAnalysis>, BeforehandCharacteristicShell<EventResultsResultAnalysis> {

	@Override
	public void initialize(Optional<EventResultsResultAnalysis> eventResultsResultAnalysisArg) {
		eventResultsResultAnalysis = eventResultsResultAnalysisArg.get();
	}

	@Override
	@Autowired
	@Qualifier("eventResultsResultAnalysisShell")
	public void initialize(BaseCharacteristic<EventResultsResultAnalysis> eventResultsResultAnalysisArg) {
		eventResultsResultAnalysis = eventResultsResultAnalysisArg.characteristic().get();
	}
}
