package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultsAssembleAnalysis;
import com.stargazerproject.analysis.EventResultsExecuteAnalysis;
import com.stargazerproject.analysis.EventResultsResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.EventResults;
import com.stargazerproject.util.JsonUtil;
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
public class BaseEventResultShell implements EventResults, BaseCharacteristic<EventResults>{
	
	/**
	* @name 事件结果内容缓存
	* @illustrate 事件结果缓存,用于存储Event 结果MAP数据
	* **/
	@Autowired
	@Qualifier("eventResultCache")
	private Cache<String, String> resultCache;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventResultShell(Optional<Cache<String, String>> resultCacheArg) {
		resultCache = resultCacheArg.get();
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
	public Optional<EventResults> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<EventResultsAssembleAnalysisHandle> resultAssemble(Optional<EventResultsAssembleAnalysis> eventResultAssembleAnalysis) {
		return eventResultAssembleAnalysis.get().analysis(Optional.of(resultCache));
	}

	/** @illustrate 事件结果内容记录器*/
	@Override
	public Optional<EventResultsExecuteAnalysisHandle> resultsExecute(Optional<EventResultsExecuteAnalysis> eventResultsExecuteAnalysis) {
		return eventResultsExecuteAnalysis.get().analysis(Optional.of(resultCache));
	}

	@Override
	public Optional<EventResultsResultAnalysisHandle> resultsResult(Optional<EventResultsResultAnalysis> eventResultsResultAnalysis) {
		return eventResultsResultAnalysis.get().analysis(Optional.of(resultCache));
	}

	@Override
	public String toString() {
		return JsonUtil.cacheToJson(Optional.of(resultCache), Optional.of("eventResultCache")).toString();
	}

}