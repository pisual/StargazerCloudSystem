package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public abstract class BaseTransactionResultAnalysisImpl implements TransactionResultAnalysis{
	
	protected TransactionResultAnalysis transactionResultAnalysis;

	@Override
	public Optional<TransactionResultAnalysisHandle> analysis(Optional<Collection<EventResult>> eventResultList) {
		return transactionResultAnalysis.analysis(eventResultList);
	}

}
