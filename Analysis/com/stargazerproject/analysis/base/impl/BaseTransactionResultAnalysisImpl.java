package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.transaction.EventResult;
import com.stargazerproject.transaction.ResultState;

import java.util.List;

public abstract class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<TransactionResultAnalysisHandle> analysis(Optional<List<EventResult>> events) {
		return transactionResultAnalysis.analysis(events);
	}

	@Override
	public Optional<ResultState> resultState() {
		return transactionResultAnalysis.resultState();
	}

}
