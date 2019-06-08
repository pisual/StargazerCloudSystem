package com.stargazerproject.analysis;

import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public interface TransactionResultAnalysis extends ResultResultAnalysis<Cache<String, String>, Collection<Event>, Cache<String, String>, TransactionResultAnalysisHandle> {
	
}
