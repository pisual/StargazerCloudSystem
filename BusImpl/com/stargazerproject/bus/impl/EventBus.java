package com.stargazerproject.bus.impl;

import com.stargazerproject.bus.exception.BusEventTimeoutException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.base.impl.BusImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Event;

@Component(value="eventBusImpl")
@Qualifier("eventBusImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBus extends BusImpl<Event, BusEventTimeoutException> implements StanderCharacteristicShell<Bus<Event, BusEventTimeoutException>>{

	public EventBus() {
		super();
		}

	@Override
	public void initialize(Optional<Bus<Event, BusEventTimeoutException>> busArg) {
		bus = busArg.get();
	}

}
