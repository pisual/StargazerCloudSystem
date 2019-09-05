package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventExecuteAnalysisImpl")
@Qualifier("eventExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventExecuteAnalysisImpl extends BaseEventExecuteAnalysisImpl implements BeforehandCharacteristicShell<EventExecuteAnalysis> {

	@Qualifier("eventExecuteAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<EventExecuteAnalysis> eventExecuteAnalysisArg) {
		eventExecuteAnalysis = eventExecuteAnalysisArg.characteristic().get();
	}

}
