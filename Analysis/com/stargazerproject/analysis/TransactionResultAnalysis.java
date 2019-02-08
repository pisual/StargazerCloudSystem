package com.stargazerproject.analysis;

import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public interface TransactionResultAnalysis extends ResultResultAnalysis<Cache<String, String>, Collection<EventResult>, TransactionResultAnalysisHandle> {
	
}
