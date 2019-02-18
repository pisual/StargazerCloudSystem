package com.stargazerproject.cache.impl.resources;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component(value="aggregateRootIndexCacheCacheLoaderCharacteristic")
@Qualifier("aggregateRootIndexCacheCacheLoaderCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AggregateRootIndexCacheCacheLoaderCharacteristic implements BaseCharacteristic<CacheLoader<String, Cache<String, String>>>{

	private CacheLoader<String, Cache<String, String>> cacheLoader;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootIndexCacheCacheLoaderCharacteristic() {}
	
	@Override
	public Optional<CacheLoader<String, Cache<String, String>>> characteristic() {
		initializationCacheLoader();
		return Optional.of(cacheLoader);
	}
	
	
	private void initializationCacheLoader(){
		cacheLoader = new CacheLoader<String, Cache<String, String>>(){
			@Override
			public Cache<String, String> load(String key) throws ExecutionException {
				Cache<String, String> aggregateRootCache = BeanContainer.instance().getBean(Optional.of("aggregateRootCache"), Cache.class);
				return aggregateRootCache;
			}
		};
	}
	
}