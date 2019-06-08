package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

/** 
 *  @name 事务结果（baseTransactionResultShell）实现
 *  @illustrate 事务结果
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseTransactionResultShell")
@Qualifier("baseTransactionResultShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseTransactionResultShell implements Result<TransactionResultAnalysis, TransactionResultAnalysisHandle, Collection<Event>, Cache<String, String>>, BaseCharacteristic<Result> {

	private static final long serialVersionUID = -4726816340050497590L;

	/**
	* @name 事件结果内容resultCache缓存
	* @illustrate 事件结果内容resultCache缓存
	* **/
	@Autowired
	@Qualifier("transactionResultCache")
	private Cache<String, String> resultCache;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseTransactionResultShell(Optional<Cache<String, String>> cacheArg) {
		resultCache = cacheArg.get();
	}

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BaseTransactionResultShell(){}
	
	@Override
	public Optional<Result> characteristic() {
		return Optional.of(this);
	}

	/** @illustrate 事件结果内容分析器*/
	@Override
	public Optional<TransactionResultAnalysisHandle> resultResult(TransactionResultAnalysis transactionResultAnalysis, Collection<Event> eventList, Cache<String, String> parametersCacheArg) {
		return transactionResultAnalysis.analysis(Optional.of(resultCache), Optional.of(eventList), Optional.of(parametersCacheArg));
	}

	@Override
	public void errorMessage(Optional<Exception> exception) {
		resultCache.put(Optional.of("ErrorMessage"), Optional.of(exception.get().getMessage()));
	}
	
	@Override
	public boolean sameValueAs(Result other) {
		return false;
	}

}
