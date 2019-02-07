package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventAssembleAnalysisImpl")
@Qualifier("eventAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventAssembleAnalysisImpl extends BaseEventAssembleAnalysisImpl implements BeforehandCharacteristicShell<EventAssembleAnalysis> {

	@Qualifier("eventAssembleAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<EventAssembleAnalysis> eventAssembleAnalysisArg) {
		eventAssembleAnalysis = eventAssembleAnalysisArg.characteristic().get();
	}
}
