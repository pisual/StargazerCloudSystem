package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventResultState;
import net.engio.mbassy.bus.IMessagePublication;

import java.util.concurrent.TimeUnit;

public class EventBusObserverAsync implements BusObserver<Event>{

	private Integer waitTimeout;

	private TimeUnit waitTimeUnit;

	private IMessagePublication iMessagePublication;

	private EventResultAnalysisHandle eventResultAnalysisHandle;

	private EventExecuteAnalysisHandle eventExecuteAnalysisHandle;

	public EventBusObserverAsync(Optional<IMessagePublication> iMessagePublicationArgs, Optional<EventExecuteAnalysisHandle> eventExecuteAnalysisHandleArg , Optional<EventResultAnalysisHandle> eventResultAnalysisHandleArg, Optional<TimeUnit> timeUnitArg, Optional<Integer> timeoutArg){
		waitTimeout = timeoutArg.get();
		waitTimeUnit = timeUnitArg.get();
		iMessagePublication = iMessagePublicationArgs.get();
		eventResultAnalysisHandle = eventResultAnalysisHandleArg.get();
		eventExecuteAnalysisHandle = eventExecuteAnalysisHandleArg.get();
	}

	@Override
	public Optional<BusObserver<Event>> waitFinish() throws BusEventTimeoutException {
		waitStart();
		waitComplete(eventExecuteAnalysisHandle.runTimeoutUnit().get(), eventExecuteAnalysisHandle.runTimeout().get());
		return Optional.of(this);
	}

	public Optional<Boolean> testFinish(){
		if(isComplete().get() == Boolean.TRUE && eventResultAnalysisHandle.getTheLastEventResultState().get() != EventResultState.WAIT){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.FALSE);
		}
	}

	@Override
	public Optional<Boolean> isComplete(){
		return Optional.of(iMessagePublication.isFinished());
	}

	@Override
	public Optional<Boolean> isRunning(){
		return Optional.of(iMessagePublication.isRunning());
	}

	@Override
	public Optional<Boolean> hasError(){
		return Optional.of(iMessagePublication.hasError());
	}

	@Override
	public Optional<Throwable> getError(){
		return Optional.fromNullable(iMessagePublication.getError().getCause());
	}

	private void waitComplete(TimeUnit runTimeUnit, Integer runTimeout) throws BusEventTimeoutException{
		for(int i=0; i<runTimeout; i++){
			if(eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.SUCCESS ||
			   eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.FAULT){
				return;
			}
			else{
				sleep(runTimeUnit);
				continue;
			}
		}
		throw new BusEventTimeoutException("Event没有在指定时间内完成任务 : BaseEvent Not Complete at the specified time : " + eventResultAnalysisHandle.getTheLastEventResultState().get());
	}

	private void waitStart() throws BusEventTimeoutException{
		for(int i=0; i<waitTimeout; i++){
			if(eventResultAnalysisHandle.getTheLastEventResultState().get() != EventResultState.WAIT) {
				return;
			}
			else{
				sleep(waitTimeUnit);
				continue;
			}
		}
		throw new BusEventTimeoutException("Event没有在指定时间内开始任务 : BaseEvent Not Start at the specified time : " + eventResultAnalysisHandle.getTheLastEventResultState().get());
	}

	private void sleep(TimeUnit timeUnit){
		try {
			switch (timeUnit) {
				case SECONDS:
					TimeUnit.SECONDS.sleep(1);
					break;
				case MICROSECONDS:
					TimeUnit.MICROSECONDS.sleep(1);
					break;
				case MILLISECONDS:
					TimeUnit.MILLISECONDS.sleep(1);
					break;
				case NANOSECONDS:
					TimeUnit.NANOSECONDS.sleep(1);
					break;
				default:
					TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}


}
