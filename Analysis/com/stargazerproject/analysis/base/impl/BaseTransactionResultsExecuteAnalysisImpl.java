package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class BaseTransactionResultsExecuteAnalysisImpl implements TransactionResultsExecuteAnalysis {
	
	protected TransactionResultsExecuteAnalysis transactionResultsExecuteAnalysis;

	@Override
	public Optional<TransactionResultsExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
		return transactionResultsExecuteAnalysis.analysis(resultCache);
	}
}
