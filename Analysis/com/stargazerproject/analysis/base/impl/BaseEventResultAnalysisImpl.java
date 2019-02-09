package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.MultimapCache;

public abstract class BaseEventResultAnalysisImpl implements EventResultAnalysis {

	protected EventResultAnalysis eventResultAnalysis;
	
	@Override
	public Optional<EventResultAnalysisHandle> analysis(Optional<MultimapCache<String, String>> executionResultCache, Optional<Cache<String, String>> interactionCache) {
		return eventResultAnalysis.analysis(executionResultCache, interactionCache);
	}

}
