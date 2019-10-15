package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

/** 
 *  @name 事件装配器接口
 *  @illustrate 实现缓存装配的基础功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface TransactionAssembleAnalysis {
	
	public Optional<TransactionAssembleAnalysisHandle> analysis(Optional<Collection<Event>> eventList, Optional<Cache<String, String>> interactionCacheArg, Optional<TransactionResultsAssembleAnalysisHandle> transactionResultsAssembleAnalysisHandle);

}
