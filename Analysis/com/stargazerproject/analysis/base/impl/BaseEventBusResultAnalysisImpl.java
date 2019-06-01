package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventBusResultAnalysis;
import com.stargazerproject.analysis.handle.EventBusResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public abstract class BaseEventBusResultAnalysisImpl implements EventBusResultAnalysis {

	protected EventBusResultAnalysis eventBusResultAnalysis;
	
	@Override
	public Optional<EventBusResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Collection<EventResult>> interactionCache) {
		return eventBusResultAnalysis.analysis(resultCache, interactionCache);
	}

}
