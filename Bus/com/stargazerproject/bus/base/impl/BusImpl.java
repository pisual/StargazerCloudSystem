package com.stargazerproject.bus.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusObserver;

public abstract class BusImpl<T, E extends Exception> implements Bus<T, E>{
	
	protected Bus<T, E> bus;

	@Override
	public Optional<BusObserver<T, E>> push(Optional<T> busEvent) throws E{
		return bus.push(busEvent);
	}

	@Override
	public Optional<BusObserver<T, E>> pushAsync(Optional<T> busEvent) {
		return bus.pushAsync(busEvent);
	}

	@Override
	public void startBus() {
		bus.startBus();
	}

	@Override
	public void stopBus() {
		bus.startBus();
	}

}
