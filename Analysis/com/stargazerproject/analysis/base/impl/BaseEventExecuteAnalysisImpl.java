package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.resources.handle.EventExecuteAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Result;

public abstract class BaseEventExecuteAnalysisImpl implements EventExecuteAnalysis {
	
	protected EventExecuteAnalysis eventExecuteAnalysis;
	
	@Override
	public Optional<EventExecuteAnalysisHandleResources> analysis(Optional<Cache<String, String>> interactionCache, Optional<Result> result) {
		return eventExecuteAnalysis.analysis(interactionCache, result);
	}
}
