package com.stargazerproject.analysis;

import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public interface TransactionResultAnalysis extends ResultResultAnalysis<MultimapCache<String, String>, Collection<Event>, TransactionResultAnalysisHandle> {
	
}
