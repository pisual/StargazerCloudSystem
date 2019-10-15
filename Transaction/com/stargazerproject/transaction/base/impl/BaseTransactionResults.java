package com.stargazerproject.transaction.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.TransactionResultsExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.transaction.TransactionResults;


/** 
 *  @name 事件结果（BaseTransactionResults）实现
 *  @illustrate BaseTransactionResults的 Shell实现
 *              
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public class BaseTransactionResults implements TransactionResults {

	protected TransactionResults transactionResults;

	protected BaseTransactionResults() {}

	@Override
	public Optional<TransactionResultsAssembleAnalysisHandle> resultAssemble(Optional<TransactionResultsAssembleAnalysis> transactionResultAssembleAnalysis) {
		return transactionResults.resultAssemble(transactionResultAssembleAnalysis);
	}

	@Override
	public Optional<TransactionResultsExecuteAnalysisHandle> resultsExecute(Optional<TransactionResultsExecuteAnalysis> transactionResultsExecuteAnalysis) {
		return transactionResults.resultsExecute(transactionResultsExecuteAnalysis);
	}

	@Override
	public Optional<TransactionResultsResultAnalysisHandle> resultsResult(Optional<TransactionResultsResultAnalysis> transactionResultsResultAnalysis) {
		return transactionResults.resultsResult(transactionResultsResultAnalysis);
	}
}