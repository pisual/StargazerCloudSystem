package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.transaction.Event;
import net.engio.mbassy.bus.IMessagePublication;

public class EventBusObserver implements BusObserver<Event>{

	private IMessagePublication iMessagePublication;

	public EventBusObserver(Optional<IMessagePublication> iMessagePublicationArgs){
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

}
