package com.stargazerproject.analysis;


import com.stargazerproject.analysis.handle.EventBusResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public interface EventBusResultAnalysis extends ResultResultAnalysis<Cache<String, String>, Collection<EventResult>, EventBusResultAnalysisHandle>{
	
}
