package com.stargazerproject.cell.impl;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.base.impl.BaseCellsTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class StandardCellsTransactionImpl extends BaseCellsTransaction<String, String>{

	@Autowired
	@Qualifier("aggregateRootCache")
	protected Cache<String, String> cacheggregateRootCache;

	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	/**
	 * @name 注入方法
	 * @illustrate 注入的方法
	 * @return <Cache<String, String>> 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param <V> 缓存的Value值
	 * **/
	public Optional<Cache<String, String>> method(Optional<Cache<String, String>> cache) {
		return Optional.of(cacheggregateRootCache);
	}
	
	public Optional<Cache<String, String>> fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		return Optional.of(cacheggregateRootCache);
    }

}
