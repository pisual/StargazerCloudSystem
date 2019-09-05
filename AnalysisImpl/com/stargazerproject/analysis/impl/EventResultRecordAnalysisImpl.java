package com.stargazerproject.analysis.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultRecordAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultRecordAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultRecordAnalysisImpl")
@Qualifier("eventResultRecordAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventResultRecordAnalysisImpl extends BaseEventResultRecordAnalysisImpl implements StanderCharacteristicShell<EventResultRecordAnalysis>, BeforehandCharacteristicShell<EventResultRecordAnalysis> {

	@Override
	public void initialize(Optional<EventResultRecordAnalysis> eventResultRecordAnalysisArg) {
		eventResultRecordAnalysis = eventResultRecordAnalysisArg.get();
	}

	@Override
	@Autowired
	@Qualifier("eventResultRecordAnalysisShell")
	public void initialize(BaseCharacteristic<EventResultRecordAnalysis> eventResultRecordAnalysisArg) {
		eventResultRecordAnalysis = eventResultRecordAnalysisArg.characteristic().get();
	}
}
