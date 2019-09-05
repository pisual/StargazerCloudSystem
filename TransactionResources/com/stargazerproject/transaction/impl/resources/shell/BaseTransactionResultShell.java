package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.TransactionResultRecordAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.TransactionResults;
import com.stargazerproject.util.JsonUtil;
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
public class BaseTransactionResultShell implements TransactionResults<TransactionResultAnalysis, TransactionResultAnalysisHandle, TransactionResultRecordAnalysis, TransactionResultRecordAnalysisHandle, Cache<String, String>, Collection<Event>>, BaseCharacteristic<TransactionResults> {

	/**
	* @name 事务结果内容缓存
	* @illustrate 事务结果内容缓存
	* **/
	@Autowired
	@Qualifier("transactionResultCache")
	private Cache<String, String> transactionResultCache;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseTransactionResultShell(Optional<Cache<String, String>> transactionResultCacheArg) {
		transactionResultCache = transactionResultCacheArg.get();
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
	public Optional<TransactionResults> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<TransactionResultAnalysisHandle> resultResult(Optional<TransactionResultAnalysis> transactionResultAnalysis, Optional<Cache<String, String>> parametersCacheArg, Optional<Collection<Event>> eventList) {
		return transactionResultAnalysis.get().analysis(Optional.of(transactionResultCache), parametersCacheArg, eventList);
	}

	@Override
	public Optional<TransactionResultRecordAnalysisHandle> resultrRcord(Optional<TransactionResultRecordAnalysis> transactionResultRecordAnalysis) {
		return transactionResultRecordAnalysis.get().analysis(Optional.of(transactionResultCache));
	}

	@Override
	public String toString() {
		return JsonUtil.cacheToJson(Optional.of(transactionResultCache), Optional.of("transactionResultCache")).toString();
	}
}
