package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventBusResourcesShell")
@Qualifier("eventBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusResourcesShell implements Bus<Event, BusEventTimeoutException>, BaseCharacteristic<Bus<Event, BusEventTimeoutException>>{

	@Autowired
	@Qualifier("eventBusBlockMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusBlockMethod<Event, BusEventTimeoutException>> eventBusBlockMethodMBassadorCharacteristic;

	@Autowired
	@Qualifier("eventBusAsyncMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusAsyncMethod<Event, BusEventTimeoutException>> eventBusAsyncMethodMBassadorCharacteristic;

	private BusBlockMethod<Event, BusEventTimeoutException> busBlockMethod;

	private BusAsyncMethod<Event, BusEventTimeoutException> busAsyncMethod;

	public EventBusResourcesShell() {}

	@Override
	public Optional<Bus<Event, BusEventTimeoutException>> characteristic() {
		busBlockMethod = eventBusBlockMethodMBassadorCharacteristic.characteristic().get();
		busAsyncMethod = eventBusAsyncMethodMBassadorCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<BusObserver<Event, BusEventTimeoutException>> push(Optional<Event> event) throws BusEventTimeoutException {
		return busBlockMethod.push(event);
	}

	@Override
	public Optional<BusObserver<Event, BusEventTimeoutException>> pushAsync(Optional<Event> event) {
		return busAsyncMethod.pushAsync(event);
	}

	@Override
	public void startBus() {

	}

	@Override
	public void stopBus() {

	}

}