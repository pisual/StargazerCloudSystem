package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class BaseTransactionResultsResultAnalysisImpl implements TransactionResultsResultAnalysis {
	
	protected TransactionResultsResultAnalysis transactionResultsResultAnalysis;

	@Override
	public Optional<TransactionResultsResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
		return transactionResultsResultAnalysis.analysis(resultCache);
	}
}
