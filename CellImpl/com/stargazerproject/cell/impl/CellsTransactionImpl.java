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
import com.stargazerproject.transaction.date.EventDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CellsTransactionImpl implements CellsTransaction<String, String> {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	/** @illustrate 聚合根缓存 **/
	@Autowired
	@Qualifier("aggregateRootCache")
	private Cache<String, String> aggregateRootCache;

	/** @illustrate 聚合根索引缓存，包含所有的聚合根缓存 **/
	@Autowired
	@Qualifier("aggregateRootIndexCache")
	private Cache<String, Cache<String, String>> aggregateRootIndexCache;

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
		Optional AggregateRootID = interactionCache.get().get(Optional.of(EventDate.AggregateRoot.toString()));
		if(AggregateRootID.get() != "NULL"){
			aggregationRootCacheInitialization(AggregateRootID);
		}
	}
	
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandleArg, Throwable throwable){
		if(throwable instanceof HystrixTimeoutException){
			log.WARN(this, HystrixTimeoutException.class.toString());
		}
		else{
			fail(throwable);
			log.ERROR(this, throwable.getMessage());
		}
	}

    protected void success(){
		eventResultsExecuteAnalysisHandle.EventResultState(Optional.of(EventResultState.SUCCESS));
	}

	protected void fail(Throwable throwable){
		eventResultsExecuteAnalysisHandle.EventResultState(Optional.of(EventResultState.FAULT));
		eventResultsExecuteAnalysisHandle.errorMessage(Optional.of(throwable));
	}

	@Override
	public void putAggregationRootCache(Optional<String> key, Optional<String> value){
		if(null == aggregateRootCache){
			log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
			throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
		}
		else{
			aggregateRootCache.put(key, value);
		}
	}

	@Override
	public Optional<String> getAggregationRootCache(Optional<String> key){
		if(null == aggregateRootCache){
			log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
			throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
		}
		else{
			return aggregateRootCache.get(key);
		}
	}

	/**
	 * @name 聚合根初始化
	 * @illustrate 聚合根初始化
	 * @return Optional<String> AggregationRootID 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param : <String> 聚合跟的Value值
	 * **/
	private void aggregationRootCacheInitialization(Optional<String> AggregationRootID){
		aggregateRootIndexCache.put(AggregationRootID, Optional.of(aggregateRootCache));
	}
}
