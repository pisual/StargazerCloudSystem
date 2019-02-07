package com.stargazerproject.bus.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.base.impl.BusObserverImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventBusObserver")
@Qualifier("eventBusObserver")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventBusObserver extends BusObserverImpl<Event> implements StanderCharacteristicShell<BusObserver<Event>>, BeforehandCharacteristicShell<BusObserver<Event>> {

	@Override
	public void initialize(Optional<BusObserver<Event>> busObserverArg) {
		busObserver = busObserverArg.get();
	}

	@Override
	@Qualifier("eventBusObserverShell")
	@Autowired
	public void initialize(BaseCharacteristic<BusObserver<Event>> busObserverArg) {
		busObserver = busObserverArg.characteristic().get();
	}
}
