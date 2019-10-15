package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsAssembleAnalysis;
import com.stargazerproject.analysis.EventResultsExecuteAnalysis;
import com.stargazerproject.analysis.EventResultsResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.transaction.EventResults;


/** 
 *  @name 事件结果（BaseEventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseEventResult implements EventResults{
	
	protected EventResults eventResults;
	
	protected BaseEventResult() {}

	@Override
	public Optional<EventResultsAssembleAnalysisHandle> resultAssemble(Optional<EventResultsAssembleAnalysis> eventResultAssembleAnalysis) {
		return eventResults.resultAssemble(eventResultAssembleAnalysis);
	}

	@Override
	public Optional<EventResultsExecuteAnalysisHandle> resultsExecute(Optional<EventResultsExecuteAnalysis> eventResultsExecuteAnalysis) {
		return eventResults.resultsExecute(eventResultsExecuteAnalysis);
	}

	@Override
	public Optional<EventResultsResultAnalysisHandle> resultsResult(Optional<EventResultsResultAnalysis> eventResultsResultAnalysis) {
		return eventResults.resultsResult(eventResultsResultAnalysis);
	}
}