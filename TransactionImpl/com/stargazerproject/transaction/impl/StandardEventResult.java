package com.stargazerproject.transaction.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.EventResults;
import com.stargazerproject.transaction.base.impl.BaseEventResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="standardEventResult")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("standardEventResult")
public class StandardEventResult extends BaseEventResult implements StanderCharacteristicShell<EventResults>, BeforehandCharacteristicShell<EventResults> {

	private static final long serialVersionUID = -5034703691118660213L;

	@Override
	public void initialize(Optional<EventResults> eventResultsArg) {
		eventResults = eventResultsArg.get();
	}

	@Override
	@Qualifier("baseEventResultShell")
	@Autowired
	public void initialize(BaseCharacteristic<EventResults> resultArg) {
		eventResults = resultArg.characteristic().get();
	}
}
