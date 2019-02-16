package com.stargazerproject.cell.impl;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.base.impl.BaseCellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.ResultState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class StandardCellsTransactionImpl extends BaseCellsTransaction<String, String>{

	@Autowired
	@Qualifier("aggregateRootCache")
	protected Cache<String, String> aggregationRootCache;

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	/**
	 * @name 注入方法
	 * @illustrate 注入的方法
	 * @return <Cache<String, String>> 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param <V> 缓存的Value值
	 * **/
	public Optional<Cache<String, String>> method(Optional<Cache<String, String>> interactionCache) {
		return Optional.of(aggregationRootCache);
	}
	
	public Optional<Cache<String, String>> fallBack(Optional<Cache<String, String>> interactionCache, Throwable throwable){
		if(throwable instanceof HystrixTimeoutException){
			log.WARN(this, HystrixTimeoutException.class.toString());
		}
		return faild(interactionCache, throwable.getMessage());
	}

    protected Optional<Cache<String, String>> success(Optional<Cache<String, String>> interactionCache){
		interactionCache.get().put(Optional.of("ResultState"), Optional.of(ResultState.SUCCESS.toString()));
		return Optional.of(aggregationRootCache);
	}

	protected Optional<Cache<String, String>> faild(Optional<Cache<String, String>> interactionCache, String message){
		interactionCache.get().put(Optional.of("ResultState"), Optional.of(ResultState.FAULT.toString()));
		return Optional.of(aggregationRootCache);
	}

}
