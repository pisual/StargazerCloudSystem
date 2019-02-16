package com.stargazerproject.cache.server.impl;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name AggregateRootIndexCache服务的实现
 *  @illustrate 继承于ServiceShell的TransactionCache相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="aggregateRootIndexCacheServer")
@Qualifier("aggregateRootIndexCacheServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AggregateRootIndexCacheServer implements StanderServiceShell{

	@Autowired
	@Qualifier("aggregateRootIndexCache")
	private StanderCharacteristicShell<Cache<String, Cache<String, String>>> aggregateRootIndexCache;

	@Autowired
	@Qualifier("aggregateRootIndexCacheShell")
	private BaseCharacteristic<Cache<String, Cache<String, String>>> aggregateRootIndexCacheShell;

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private AggregateRootIndexCacheServer() {}

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootIndexCacheServer(Optional<BaseCharacteristic<Cache<String, Cache<String, String>>>> aggregateRootIndexCacheShellArg, Optional<StanderCharacteristicShell<Cache<String, Cache<String, String>>>> aggregateRootIndexCacheArg){
		aggregateRootIndexCacheShell = aggregateRootIndexCacheShellArg.get();
		aggregateRootIndexCache = aggregateRootIndexCacheArg.get();
	}
	
	/** @illustrate 启动服务及相关操作
	 *  @ThreadSafeMethodsLevel startUp方法的线程安全级别是 ThreadSafeLevel.ThreadCompatible，非线程安全方法
	 *  **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	@Override
	public void startUp() {
		aggregateRootIndexCache.initialize(aggregateRootIndexCacheShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 
	 *  @ThreadSafeMethodsLevel shutDown方法的线程安全级别是 ThreadSafeLevel.ThreadCompatible，非线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadCompatible)
	@Override
	public void shutDown() {
	}
}