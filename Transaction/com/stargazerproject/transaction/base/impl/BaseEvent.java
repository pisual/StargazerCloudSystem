package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventState;

/** 
 *  @name 事件（BaseEvent）模型
 *  @illustrate 事件（BaseEvent）模型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseEvent extends ID implements Event{
	
	private static final long serialVersionUID = 1122535382679831080L;
	
	protected Event event;
	
	protected BaseEvent() {}

	@Override
	public Optional<EventAssembleAnalysisHandle> eventAssemble(Optional<EventAssembleAnalysis> eventAssembleAnalysis) {
		return event.eventAssemble(eventAssembleAnalysis);
	}

	@Override
	public Optional<EventExecuteAnalysisHandle> eventExecute(Optional<EventExecuteAnalysis> eventAnalysis) {
		return event.eventExecute(eventAnalysis);
	}

	@Override
	public Optional<EventResultAnalysisHandle> eventResult(Optional<EventResultAnalysis> eventResultAnalysis) {
		return event.eventResult(eventResultAnalysis);
	}

	@Override
	public void skipEvent() {
		event.skipEvent();
	}

	@Override
	public Optional<EventState> eventState() {
		return event.eventState();
	}

	@Override
	public Optional<String> sequenceID(){
		return event.sequenceID();
	}

	@Override
	public void injectSequenceID(Optional<String> idArg) {
		event.injectSequenceID(idArg);
	}


	public String toString(){
		return event.toString();
	}
}