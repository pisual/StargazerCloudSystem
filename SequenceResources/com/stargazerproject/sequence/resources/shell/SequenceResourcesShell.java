package com.stargazerproject.sequence.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.exception.EventException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Event>, BaseCharacteristic<Sequence<Event>>{

	@Autowired
	@Qualifier("sequenceEventsCharacteristic")
	private BaseCharacteristic<SequenceTransaction<Event>> sequenceEventsCharacteristic;

	private SequenceTransaction<Event> sequenceTransaction;

	@Autowired
	@Qualifier("parallelSequenceEventsCharacteristic")
	private BaseCharacteristic<ParallelSequenceTransaction<Event>> parallelSequenceEventsCharacteristic;

	private ParallelSequenceTransaction<Event> parallelSequenceTransaction;

	@Override
	public Optional<Sequence<Event>> characteristic() {
		sequenceTransaction = sequenceEventsCharacteristic.characteristic().get();
		parallelSequenceTransaction = parallelSequenceEventsCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<ParallelSequenceTransaction<Event>> creatParallelSequence() {
		return parallelSequenceTransaction.creatParallelSequence();
	}

	@Override
	public void addParallelSequence(Optional<Event> event) {
		parallelSequenceTransaction.addParallelSequence(event);
	}

	@Override
	public void clearParallelSequence() {
		parallelSequenceTransaction.clearParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<Event>> startBlockParallelSequence() throws BusEventTimeoutException, EventException {
		return parallelSequenceTransaction.startBlockParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<Event>> startParallelSequence() {
		return parallelSequenceTransaction.startParallelSequence();
	}

	@Override
	public Optional<SequenceObserver<Event>> shutDownParallelSequence() {
		return parallelSequenceTransaction.shutDownParallelSequence();
	}

	@Override
	public Optional<SequenceTransaction<Event>> creatSequence() {
		return sequenceTransaction.creatSequence();
	}

	@Override
	public void addSequence(Optional<Event> event) {
		sequenceTransaction.addSequence(event);
	}

	@Override
	public void clearSequence() {
		sequenceTransaction.clearSequence();
	}

	@Override
	public Optional<SequenceObserver<Event>> startBlockSequence() throws BusEventTimeoutException, EventException {
		return sequenceTransaction.startBlockSequence();
	}

	@Override
	public Optional<SequenceObserver<Event>> startSequence() {
		return sequenceTransaction.startSequence();
	}
	
}