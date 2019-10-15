package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name Result分析接口
 *  @illustrate 分析Result
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionResultsResult{

	/** @illustrate 结果分析器，分析者调用
	 *  @ThreadSafeMethodsLevel resultResult的线程安全级别为ThreadSafeLevel.ThreadCompatible，非线程安全，只能单线程单次使用
	 *  **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	public Optional<TransactionResultsResultAnalysisHandle> resultsResult(Optional<TransactionResultsResultAnalysis> transactionResultsResultAnalysis);
}
