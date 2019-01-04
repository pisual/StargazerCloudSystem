package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.transaction.EventExecute;

import java.util.Collection;

public interface TransactionExecuteAnalysis {
	
	/** @illustrate 执行分析器 **/
	public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<EventExecute>> eventList);

}
