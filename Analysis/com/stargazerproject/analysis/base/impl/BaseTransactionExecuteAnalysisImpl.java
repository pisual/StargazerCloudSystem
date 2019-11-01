package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.TransactionState;

import java.util.Collection;

public abstract class BaseTransactionExecuteAnalysisImpl implements TransactionExecuteAnalysis {
	
	protected TransactionExecuteAnalysis transactionExecuteAnalysis;

	@Override
	public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<Event>> eventList, Optional<Cache<String, String>> transactionInteractionCache, Optional<TransactionState> transactionState, Optional<TransactionResultsExecuteAnalysisHandle> transactionResultsExecuteAnalysisHandle) {
		return transactionExecuteAnalysis.analysis(eventList, transactionInteractionCache, transactionState, transactionResultsExecuteAnalysisHandle);
	}

}
