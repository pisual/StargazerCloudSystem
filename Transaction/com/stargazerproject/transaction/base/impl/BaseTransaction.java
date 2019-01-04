package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.exception.TransactionException;

public class BaseTransaction extends ID implements Transaction{

	private static final long serialVersionUID = -3595408225181602700L;
	
	protected Transaction transaction;

	@Override
	public Optional<TransactionAssembleAnalysisHandle> transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis) {
		return transaction.transactionAssemble(transactionAssembleAnalysis);
	}

	@Override
	public Optional<TransactionResultAnalysisHandle> transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysis) {
		return transaction.transactionResult(transactionResultAnalysis);
	}

	@Override
	public Optional<TransactionExecuteAnalysisHandle> transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis) throws TransactionException {
		return transaction.transactionExecute(transactionExecuteAnalysis);
	}

	@Override
	public void skipTransaction() {
		transaction.skipTransaction();
	}
	
}
