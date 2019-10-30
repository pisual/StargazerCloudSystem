package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventResultState;

import java.util.concurrent.TimeUnit;

public class EventBusObserver implements BusObserver<Event, BusEventTimeoutException>{

	private EventResultAnalysisHandle eventResultAnalysisHandle;

	private EventExecuteAnalysisHandle eventExecuteAnalysisHandle;

	public EventBusObserver(Optional<EventExecuteAnalysisHandle> eventExecuteAnalysisHandleArg , Optional<EventResultAnalysisHandle> eventResultAnalysisHandleArg){
		eventResultAnalysisHandle = eventResultAnalysisHandleArg.get();
		eventExecuteAnalysisHandle = eventExecuteAnalysisHandleArg.get();
	}

	@Override
	public Optional<BusObserver<Event, BusEventTimeoutException>> waitFinish() throws BusEventTimeoutException {
		waitStart();
		waitComplete(eventExecuteAnalysisHandle.runTimeoutUnit().get(), eventExecuteAnalysisHandle.runTimeout().get());
		return Optional.of(this);
	}

	@Override
	public Optional<Boolean> testFinish(){
		if(isComplete().get() == Boolean.TRUE){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.FALSE);
		}
	}

	@Override
	public Optional<Boolean> isComplete(){
		if(eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.SUCCESS ||
		  eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.FAULT){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.TRUE);
		}
	}

	@Override
	public Optional<Boolean> isRunning(){
		if(eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.Run){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.TRUE);
		}
	}

	@Override
	public Optional<Boolean> hasError(){
		eventResultAnalysisHandle.getTheLastErrorMessage();
		return Optional.of(eventResultAnalysisHandle.getTheLastErrorMessage().isPresent());
	}

	@Override
	public Optional<String> getError(){
		return eventResultAnalysisHandle.getTheLastErrorMessage();
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
		Integer waitTimeout = eventExecuteAnalysisHandle.waitTimeout().get();
		TimeUnit  waitTimeoutUni = eventExecuteAnalysisHandle.waitTimeoutUnit().get();
		for(int i=0; i<waitTimeout; i++){
			if(eventResultAnalysisHandle.getTheLastEventResultState().get() != EventResultState.WAIT) {
				return;
			}
			else{
				sleep(waitTimeoutUni);
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
