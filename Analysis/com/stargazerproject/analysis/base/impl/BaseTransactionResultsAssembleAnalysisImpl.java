package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class BaseTransactionResultsAssembleAnalysisImpl implements TransactionResultsAssembleAnalysis {
	
	protected TransactionResultsAssembleAnalysis transactionResultsAssembleAnalysis;

	@Override
	public Optional<TransactionResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
		return transactionResultsAssembleAnalysis.analysis(resultCache);
	}
}
