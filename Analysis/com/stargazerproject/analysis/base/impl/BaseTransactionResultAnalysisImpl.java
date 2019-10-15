package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> parametersCache, Optional<Collection<Event>> eventList, Optional<TransactionResultsResultAnalysisHandle> transactionResultsResultAnalysisHandleArg) {
		return transactionResultAnalysis.analysis(parametersCache, eventList, transactionResultsResultAnalysisHandleArg);
	}
}
