package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.EventResultRecordAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResults;


/** 
 *  @name 事件结果（BaseEventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseEventResult implements EventResults<EventResultAnalysis, EventResultAnalysisHandle, EventResultRecordAnalysis, EventResultRecordAnalysisHandle, Cache<String, String>> {
	
	protected EventResults eventResults;
	
	protected BaseEventResult() {}
	
	@Override
	public Optional<EventResultAnalysisHandle> resultResult(Optional<EventResultAnalysis> eventResultAnalysis, Optional<Cache<String, String>> parametersCache) {
		return eventResults.resultResult(eventResultAnalysis, parametersCache);
	}

	@Override
	public Optional<EventResultRecordAnalysisHandle> resultrRcord(Optional<EventResultRecordAnalysis> resultRecordAnalysis) {
		return eventResults.resultrRcord(resultRecordAnalysis);
	}

}