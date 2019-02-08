package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public abstract class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Collection<EventResult>> interactionCache) {
		return transactionResultAnalysis.analysis(resultCache, interactionCache);
	}

}
