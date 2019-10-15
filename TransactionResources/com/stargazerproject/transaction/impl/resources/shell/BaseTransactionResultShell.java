package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.TransactionResultsExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.TransactionResults;
import com.stargazerproject.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 事务结果（baseTransactionResultShell）实现
 *  @illustrate 事务结果
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseTransactionResultShell")
@Qualifier("baseTransactionResultShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseTransactionResultShell implements TransactionResults, BaseCharacteristic<TransactionResults> {

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
	public Optional<TransactionResultsAssembleAnalysisHandle> resultAssemble(Optional<TransactionResultsAssembleAnalysis> transactionResultAssembleAnalysis) {
		return transactionResultAssembleAnalysis.get().analysis(Optional.of(transactionResultCache));
	}

	@Override
	public Optional<TransactionResultsExecuteAnalysisHandle> resultsExecute(Optional<TransactionResultsExecuteAnalysis> transactionResultsExecuteAnalysis) {
		return transactionResultsExecuteAnalysis.get().analysis(Optional.of(transactionResultCache));
	}

	@Override
	public Optional<TransactionResultsResultAnalysisHandle> resultsResult(Optional<TransactionResultsResultAnalysis> transactionResultsResultAnalysis) {
		return transactionResultsResultAnalysis.get().analysis(Optional.of(transactionResultCache));
	}

	@Override
	public String toString() {
		return JsonUtil.cacheToJson(Optional.of(transactionResultCache), Optional.of("transactionResultCache")).toString();
	}
}
