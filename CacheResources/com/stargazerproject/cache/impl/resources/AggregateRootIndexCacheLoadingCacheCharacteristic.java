package com.stargazerproject.cache.impl.resources;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component(value="aggregateRootIndexCacheLoadingCacheCharacteristic")
@Qualifier("aggregateRootIndexCacheLoadingCacheCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AggregateRootIndexCacheLoadingCacheCharacteristic implements BaseCharacteristic<LoadingCache<String, Cache<String, String>>>{

	/** @name 缓存最大数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_AggregateRootIndexCache_MaxSize;

	/** @name 缓存初始化数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_AggregateRootIndexCache_InitialSize;

	/** @name 缓存并行级别 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ConcurrencyLevel;

	/** @name 缓存非读销毁时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterReadTime;

	/** @name 缓存非写销毁时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterWriteTime;

	/**
	* @name cacheLoader
	* @illustrate Guava cacheLoader
	* **/
	@Autowired
	@Qualifier("aggregateRootIndexCacheCacheLoaderCharacteristic")
	private BaseCharacteristic<CacheLoader<String, Cache<String, String>>> cacheLoader;

	/**
	* @name removalListener
	* @illustrate 移除监听器
	* **/
	@Autowired
	@Qualifier("aggregateRootIndexCacheRemovalListenerCharacteristic")
	private BaseCharacteristic<RemovalListener<String, Cache<String, String>>> removalListener;

	/**
	* @name LoadingCache
	* @illustrate Guava loadingCache
	* **/
	private LoadingCache<String, Cache<String, String>> loadingCache;

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private AggregateRootIndexCacheLoadingCacheCharacteristic() {}

	
	@Override
	public Optional<LoadingCache<String, Cache<String, String>>> characteristic() {
		loadingCacheBuilder();
		return Optional.of(loadingCache);
	}
	
	private void loadingCacheBuilder(){
	     loadingCache = CacheBuilder
					   .newBuilder()
					   .concurrencyLevel(getIntegerParameter(Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ConcurrencyLevel))
					   .expireAfterWrite(getIntegerParameter(Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterWriteTime), TimeUnit.MILLISECONDS)
					   .initialCapacity(getIntegerParameter(Parameters_Module_Kernel_Cache_AggregateRootIndexCache_InitialSize))
					   .maximumSize(getIntegerParameter(Parameters_Module_Kernel_Cache_AggregateRootIndexCache_MaxSize))
					   .expireAfterAccess(getIntegerParameter(Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterReadTime), TimeUnit.MILLISECONDS)
					   .removalListener(removalListener.characteristic().get())
					   .build(cacheLoader.characteristic().get());
	     }
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Parameters_Module_Kernel_Cache_AggregateRootIndexCache_MaxSize", Parameters_Module_Kernel_Cache_AggregateRootIndexCache_MaxSize)
                .add("Parameters_Module_Kernel_Cache_AggregateRootIndexCache_InitialSize", Parameters_Module_Kernel_Cache_AggregateRootIndexCache_InitialSize)
                .add("Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ConcurrencyLevel", Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ConcurrencyLevel)
                .add("Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterReadTime", Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterReadTime)
                .add("Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterWriteTime", Parameters_Module_Kernel_Cache_AggregateRootIndexCache_ExpireAfterWriteTime).toString();
	}
}