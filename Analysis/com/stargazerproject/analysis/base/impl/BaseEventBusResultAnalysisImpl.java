package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventBusResultAnalysis;
import com.stargazerproject.analysis.handle.EventBusResultAnalysisHandle;
import com.stargazerproject.bus.BusEventTimeoutModel;
import com.stargazerproject.transaction.EventResult;

import java.util.List;

public abstract class BaseEventBusResultAnalysisImpl implements EventBusResultAnalysis {

	protected EventBusResultAnalysis eventBusResultAnalysis;
	
	@Override
	public Optional<EventBusResultAnalysisHandle> analysis(Optional<List<EventResult>> resultCacheArg) {
		return eventBusResultAnalysis.analysis(resultCacheArg);
	}

	@Override
	public Optional<BusEventTimeoutModel> busEventTimeout() {
		return eventBusResultAnalysis.busEventTimeout();
	}

}
