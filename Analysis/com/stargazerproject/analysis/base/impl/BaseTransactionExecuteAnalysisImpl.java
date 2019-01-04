package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.transaction.EventExecute;

import java.util.Collection;

public abstract class BaseTransactionExecuteAnalysisImpl implements TransactionExecuteAnalysis {
	
	protected TransactionExecuteAnalysis transactionExecuteAnalysis;

	@Override
	public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<EventExecute>> eventList) {
		return transactionExecuteAnalysis.analysis(eventList);
	}

}
