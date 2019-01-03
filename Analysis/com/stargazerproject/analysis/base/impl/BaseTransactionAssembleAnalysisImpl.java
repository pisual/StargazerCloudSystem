package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public abstract class BaseTransactionAssembleAnalysisImpl implements TransactionAssembleAnalysis {
	
	protected TransactionAssembleAnalysis transactionAssembleAnalysis;

	@Override
	public Optional<TransactionAssembleAnalysisHandle> analysis(Optional<Collection<Event>> eventsList) {
		return transactionAssembleAnalysis.analysis(eventsList);
	}

}
