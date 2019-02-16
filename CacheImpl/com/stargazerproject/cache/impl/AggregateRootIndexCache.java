package com.stargazerproject.cache.impl;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name aggregateRootIndex(聚合根索引)缓存
 *  @illustrate 聚合根索引缓存
 *              一旦超过最长等待处理时间，将抛弃此事务，通过调用Transaction的Skip来主动放弃并进行快速失败
 *  @Shell Cache<String, Cache<String, String>>，Map类型的通用接口
 *  @param :<String> 缓存的Key值类型
 *  @param :<Cache<String, String>> 缓存的Value类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="aggregateRootIndexCache")
@Qualifier("aggregateRootIndexCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class AggregateRootIndexCache extends BaseCacheImpl<String, Cache<String, String>> implements StanderCharacteristicShell<Cache<String,Cache<String, String>>>{

	private static final long serialVersionUID = -6399058194166202599L;

	/** @construction 初始化构造 **/
	public AggregateRootIndexCache() {}

	@Override
	public void initialize(Optional<Cache<String, Cache<String, String>>> cacaheArg) {
		cache = cacaheArg.get();
	}

}