package com.stargazerproject.transaction.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.base.impl.BaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="standardEvent")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("standardEvent")
public class StandardEvent extends BaseEvent implements StanderCharacteristicShell<Event>, BeforehandCharacteristicShell<Event> {

	private static final long serialVersionUID = 9027890577069473120L;

	@Override
	public void initialize(Optional<Event> eventArg) {
		event = eventArg.get();
	}

	@Override
	@Qualifier("baseEventShell")
	@Autowired
	public void initialize(BaseCharacteristic<Event> eventArg) {
		event = eventArg.characteristic().get();
	}
}
