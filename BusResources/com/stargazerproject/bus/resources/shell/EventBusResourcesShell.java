package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component(value="eventBusResourcesShell")
@Qualifier("eventBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusResourcesShell implements Bus<Event>, BaseCharacteristic<Bus<Event>>{

	@Autowired
	@Qualifier("eventBusBlockMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusBlockMethod<Event>> eventBusBlockMethodMBassadorCharacteristic;

	@Autowired
	@Qualifier("eventBusAsyncMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusAsyncMethod<Event>> eventBusAsyncMethodMBassadorCharacteristic;

	private BusBlockMethod<Event> busBlockMethod;

	private BusAsyncMethod<Event> busAsyncMethod;

	public EventBusResourcesShell() {}

	@Override
	public Optional<Bus<Event>> characteristic() {
		busBlockMethod = eventBusBlockMethodMBassadorCharacteristic.characteristic().get();
		busAsyncMethod = eventBusAsyncMethodMBassadorCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<BusObserver<Event>> push(Optional<Event> event, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		return busBlockMethod.push(event, timeUnit, timeout);
	}

	@Override
	public Optional<BusObserver<Event>> pushAsync(Optional<Event> event, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		return busAsyncMethod.pushAsync(event, timeUnit, timeout);
	}

	@Override
	public void startBus() {

	}

	@Override
	public void stopBus() {

	}

}