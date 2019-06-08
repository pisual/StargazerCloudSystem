package com.stargazerproject.cell.impl;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.base.impl.BaseCellsTransaction;
import com.stargazerproject.cell.method.exception.RunException;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.ResultState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class StandardCellsTransactionImpl extends BaseCellsTransaction<String, String, String, String>{

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	@Autowired
	@Qualifier("aggregateRootIndexCache")
	private Cache<String, Cache<String, String>> aggregateRootIndexCache;

	/** @illustrate 聚合根缓存 **/
	private Cache<String, String> aggregateRootCache;

	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	/**
	 * @name 注入方法
	 * @illustrate 注入的方法
	 * @return <Cache<String, String>> 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param <V> 缓存的Value值
	 * **/
	public void method(Optional<Cache<String, String>> interactionCache, Optional<Cache<String, String>> resultCache) {
		aggregationRootCacheAcquire(resultCache);
	}
	
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<Cache<String, String>> resultCache, Throwable throwable){
		if(throwable instanceof HystrixTimeoutException){
			log.WARN(this, HystrixTimeoutException.class.toString());
		}
		else{
			faild(resultCache, throwable.getMessage());
		}
	}

    protected void success(Optional<Cache<String, String>> resultCache){
		resultCache.get().put(Optional.of("ResultState"), Optional.of(ResultState.SUCCESS.toString()));
	}

	protected void faild(Optional<Cache<String, String>> resultCache, String message){
		resultCache.get().put(Optional.of("ResultState"), Optional.of(ResultState.FAULT.toString()));
		log.FATAL(this, message);
	}

	protected void putAggregationRootCache(Optional<String> key, Optional<String> value){
		if(null == aggregateRootCache){
			log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
			throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
		}
		else{
			aggregateRootCache.put(key, value);
		}
	}

	protected Optional<String> getAggregationRootCache(Optional<String> key){
		if(null == aggregateRootCache){
			log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
			throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
		}
		else{
			return aggregateRootCache.get(key);
		}
	}

	private void aggregationRootCacheAcquire(Optional<Cache<String, String>> interactionCache){
		Optional<String> aggregateRootCacheIndex = interactionCache.get().get(Optional.of("AggregateRootCacheIndex"));

		if(aggregateRootCacheIndex.isPresent()){
			aggregateRootCache = aggregateRootIndexCache.get(aggregateRootCacheIndex).get();
		}
		else{
			throw new RunException("没有启用聚合根索引");
		}

	}
}
