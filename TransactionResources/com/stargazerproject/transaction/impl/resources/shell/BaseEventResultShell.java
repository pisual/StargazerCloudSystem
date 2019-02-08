package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Result;
import com.stargazerproject.transaction.ResultRecord;
import com.stargazerproject.transaction.ResultState;
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
public class BaseEventResultShell implements Result<EventResultAnalysisHandle>, BaseCharacteristic<Result>{

	private static final long serialVersionUID = -4726816340050497590L;
	
	/**
	* @name 事件结果内容缓存
	* @illustrate 事件结果内容缓存
	* **/
	@Autowired
	@Qualifier("eventResultCache")
	private Cache<String, String> executionResultCache;

	/**
	 * @name 交互缓存接口
	 * @illustrate 交互缓存接口
	 * **/
	private Cache<String, String> interactionCache;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventResultShell(Optional<Cache<String, String>> executionResultCacheArg) {
		executionResultCache = executionResultCacheArg.get();
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
		return Optional.of(this);
	}

	/** @illustrate 事件结果内容分析器*/
	@Override
	public Optional<EventResultAnalysisHandle> resultResult(EventResultAnalysis eventResultAnalysis) {
		return eventResultAnalysis.analysis(Optional.of(executionResultCache));
	}

	/** @illustrate 设置事件结果标志为完成状态，**/
	@Override
	public Optional<ResultRecord> complete(Optional<ResultState> resultState) {
		executionResultCache.put(Optional.of("ResultState"),
				                 Optional.of(ResultState.SUCCESS.toString()));
		return Optional.of(this);
	}

	@Override
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception) {
		addCacheContent(errorMessage.get(), exception.get().fillInStackTrace().toString());
		return null;
	}
	
	/**
	* @name cache Add方法
	* @illustrate 在Cache现有内容上添加新的内容
	* @param Optional<String> errorMessage 异常信息
	* @param Optional<Exception> exception 异常Exception
	* **/
	private void addCacheContent(String errorMessage, String exception){
//		Optional<String> cacheErrorMessage = executionResultCache.get(Optional.of("ErrorMessage"));
//
//		if(cacheErrorMessage.isPresent()){
//			cacheErrorMessage = Optional.of(cacheErrorMessage.get() + ";" + errorMessage + ":" + exception);
//
//		}
//		else{
//			cacheErrorMessage = Optional.of(errorMessage + ":" + exception);
//		}
//
//		executionResultCache.put(Optional.of("ErrorMessage"),
//								cacheErrorMessage);

	}
	
	@Override
	public boolean sameValueAs(Result other) {
		return false;
	}

}
