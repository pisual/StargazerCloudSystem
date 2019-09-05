package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultRecordAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class BaseTransactionResultRecordAnalysisImpl implements TransactionResultRecordAnalysis {
	
	protected TransactionResultRecordAnalysis transactionResultRecordAnalysis;

	@Override
	public Optional<TransactionResultRecordAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
		return transactionResultRecordAnalysis.analysis(resultCache);
	}
}
