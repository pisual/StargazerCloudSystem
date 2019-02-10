package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Result;
import com.stargazerproject.transaction.ResultRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 事件结果（BaseEventResult）实现
 *  @illustrate 事件结果是对于事务运行结果相关内容的聚合，包含了:
 *              executionResult : 运行结果缓存
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseEventResultShell")
@Qualifier("baseEventResultShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseEventResultShell implements Result<EventResultAnalysis, EventResultAnalysisHandle, Cache<String, String>>, BaseCharacteristic<Result>{

	private static final long serialVersionUID = -4726816340050497590L;
	
	/**
	* @name 事件结果内容Multimap缓存
	* @illustrate 事件结果内容Multimap缓存
	* **/
	private MultimapCache<String, String> resultMultimapCache;

	@Autowired
	@Qualifier("eventResultMultimapCache")
	private StanderCharacteristicShell<MultimapCache<String, String>> eventResultMultimapCache;

	@Autowired
	@Qualifier("eventResultMultimapCacheShell")
	private BaseCharacteristic<MultimapCache<String, String>> eventResultMultimapCacheShell;


	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventResultShell(Optional<MultimapCache<String, String>> resultMultimapCacheArg) {
		resultMultimapCache = resultMultimapCacheArg.get();
	}
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BaseEventResultShell(){}
	
	@Override
	public Optional<Result> characteristic() {
		eventResultMultimapCache.initialize(eventResultMultimapCacheShell.characteristic());
		resultMultimapCache = eventResultMultimapCacheShell.characteristic().get();
		return Optional.of(this);
	}

	/** @illustrate 事件结果内容分析器*/
	@Override
	public Optional<EventResultAnalysisHandle> resultResult(EventResultAnalysis eventResultAnalysis, Cache<String, String> interactionCacheArg) {
		return eventResultAnalysis.analysis(Optional.of(resultMultimapCache), Optional.of(interactionCacheArg));
	}

	@Override
	public Optional<ResultRecord> errorMessage(Optional<Exception> exception) {
		resultMultimapCache.put(Optional.of("ErrorMessage"), Optional.of(exception.get().getMessage()));
		return null;
	}
	
	@Override
	public boolean sameValueAs(Result other) {
		return false;
	}

}
