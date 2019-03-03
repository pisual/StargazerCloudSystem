package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<TransactionResultAnalysisHandle> analysis(Optional<MultimapCache<String, String>> resultCache, Optional<Collection<Event>> interactionCache) {
		return transactionResultAnalysis.analysis(resultCache, interactionCache);
	}

}
