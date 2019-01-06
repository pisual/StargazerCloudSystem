package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.exception.EventException;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;

public class BaseSequence<K> implements Sequence<K>{
	
	protected Sequence<K> sequence;

	@Override
	public Optional<ParallelSequenceTransaction<K>> creatParallelSequence() {
		return sequence.creatParallelSequence();
	}

	@Override
	public void addParallelSequence(Optional<K> transaction) {
		sequence.addParallelSequence(transaction);
	}

	@Override
	public void clearParallelSequence() {
		sequence.clearParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<K>> startBlockParallelSequence() throws BusEventTimeoutException {
		return sequence.startBlockParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<K>> startParallelSequence() {
		return sequence.startParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<K>> shutDownParallelSequence() {
		return sequence.shutDownParallelSequence();
	}

	@Override
	public Optional<SequenceTransaction<K>> creatSequence() {
		return sequence.creatSequence();
	}

	@Override
	public void addSequence(Optional<K> transaction) {
		sequence.addSequence(transaction);
	}

	@Override
	public void clearSequence() {
		sequence.clearSequence();
	}

	@Override
	public Optional<SequenceObserver<K>> startBlockSequence() throws BusEventTimeoutException, EventException {
		return sequence.startBlockSequence();
	}

	@Override
	public Optional<SequenceObserver<K>> startSequence() {
		return sequence.startSequence();
	}

}
