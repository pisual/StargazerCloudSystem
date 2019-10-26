package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.Transaction;
import net.engio.mbassy.bus.IMessagePublication;

public class TransactionBusObserver implements BusObserver<Transaction>{

	private IMessagePublication iMessagePublication;

	public TransactionBusObserver(Optional<IMessagePublication> iMessagePublicationArgs){
		iMessagePublication = iMessagePublicationArgs.get();
	}

	public Optional<Boolean> isComplete(){
		return Optional.of(iMessagePublication.isFinished());
	}

	public Optional<Boolean> isRunning(){
		return Optional.of(iMessagePublication.isRunning());
	}

	public Optional<Boolean> hasError(){
		return Optional.of(iMessagePublication.isFinished());
	}

	public Optional<Throwable> getError(){
		return Optional.of(iMessagePublication.getError().getCause());
	}

	@Override
	public Optional<BusObserver<Event>> waitFinish() throws BusEventTimeoutException {
		return null;
	}

}
