package com.stargazerproject.sequence;

import com.google.common.base.Optional;

/** 
 *  @name 序列的结果观测者
 *  @illustrate 序列的结果观测者
 *  @author Felixerio
 *  **/
public interface SequenceObserver<T> {

	public Optional<Boolean> isComplete();

	public Optional<Boolean> isRunning();

	public Optional<Boolean> hasError();

	public Optional<Throwable> getError();
	
}
