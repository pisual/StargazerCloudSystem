package com.stargazerproject.cell.impl;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.EventResultState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CellsTransactionImpl implements CellsTransaction<String, String> {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	private EventResultsExecuteAnalysisHandle eventResultsExecuteAnalysisHandle;

	/**
	 * @name 调用方法
	 * @illustrate 注入的方法
	 * @return <Cache<String, String>> 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param : <V> 缓存的Value值
	 * **/
	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg) {
		eventResultsExecuteAnalysisHandle = eventResultsExecuteAnalysisHandleArg.get();
	}
	
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg, Throwable throwable){
		if(throwable instanceof HystrixTimeoutException){
			log.WARN(this, HystrixTimeoutException.class.toString());
		}
		else{
			fail(throwable);
		}
	}

    protected void success(){
		eventResultsExecuteAnalysisHandle.EventResultState(Optional.of(EventResultState.SUCCESS));
	}

	protected void fail(Throwable throwable){
		eventResultsExecuteAnalysisHandle.EventResultState(Optional.of(EventResultState.FAULT));
		eventResultsExecuteAnalysisHandle.errorMessage(Optional.of(throwable));
	}

}
