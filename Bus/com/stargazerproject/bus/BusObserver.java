package com.stargazerproject.bus;

import com.google.common.base.Optional;

/**
 *  @name Bus的观测者
 *  @illustrate 总线Bus的观测者
 *  @author Felixerio
 *  **/
public interface BusObserver<T, E extends Exception> {

	public Optional<Boolean> isComplete();

	public Optional<Boolean> isRunning();

	public Optional<Boolean> hasError();

	public Optional<String> getError();

	public Optional<BusObserver<T, E>> waitFinish() throws E;
}
