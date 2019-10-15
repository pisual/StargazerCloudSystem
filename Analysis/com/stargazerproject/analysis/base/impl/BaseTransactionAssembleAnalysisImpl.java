package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public abstract class BaseTransactionAssembleAnalysisImpl implements TransactionAssembleAnalysis {
	
	protected TransactionAssembleAnalysis transactionAssembleAnalysis;

	@Override
	public Optional<TransactionAssembleAnalysisHandle> analysis(Optional<Collection<Event>> eventsList, Optional<Cache<String, String>> interactionCacheArg, Optional<TransactionResultsAssembleAnalysisHandle> transactionResultsAssembleAnalysisHandle) {
		return transactionAssembleAnalysis.analysis(eventsList, interactionCacheArg, transactionResultsAssembleAnalysisHandle);
	}

}
