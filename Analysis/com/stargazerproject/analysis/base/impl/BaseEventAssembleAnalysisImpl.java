package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public abstract class BaseEventAssembleAnalysisImpl implements EventAssembleAnalysis {
	
	protected EventAssembleAnalysis eventAssembleAnalysis;
	
	@Override
	public Optional<EventAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> interactionCache) {
		return eventAssembleAnalysis.analysis(interactionCache);
	}

}
