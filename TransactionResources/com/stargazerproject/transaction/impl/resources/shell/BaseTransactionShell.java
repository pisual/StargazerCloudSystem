package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.*;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.*;
import com.stargazerproject.transaction.base.impl.ID;
import com.stargazerproject.util.JsonUtil;
import com.stargazerproject.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/** 
 *  @name 事务（baseTransaction）实现
 *  @illustrate 事务（baseTransaction）是事件的原子聚合体
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseTransactionShell")
@Qualifier("baseTransactionShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseTransactionShell extends ID implements Transaction, BaseCharacteristic<Transaction>{

	/** 注入区 Start **/

	/**@illustrate 日志接口**/
	@Autowired
	@Qualifier("logRecord")
	public LogMethod logMethod;

	/** @illustrate 参数缓存接口 **/
	@Autowired
	@Qualifier("transactionInteractionCache")
	public Cache<String, String> parametersCache;

	/** @illustrate Event Result Shell 实例**/
	@Autowired
	@Qualifier("baseTransactionResultShell")
	private BaseCharacteristic<TransactionResults> baseTransactionResultShell;

	@Autowired
	@Qualifier("transactionResultsAssembleAnalysisImpl")
	private TransactionResultsAssembleAnalysis transactionResultsAssembleAnalysis;

	/** @illustrate eventResultRecordAnalysis实例**/
	@Autowired
	@Qualifier("transactionResultsExecuteAnalysisImpl")
	private TransactionResultsExecuteAnalysis transactionResultsExecuteAnalysis;

	@Autowired
	@Qualifier("transactionResultsResultAnalysisImpl")
	private TransactionResultsResultAnalysis transactionResultsResultAnalysis;

	/** 注入区 End **/

	/** 变量区 Start **/

	/** @illustrate Event列表**/
	private Collection<Event> eventsList;

	/** @illustrate Result接口**/
	private TransactionResults transactionResults;

	/** @illustrate 事件状态, 初始状态为初始态**/
	private TransactionState transactionState = TransactionState.INIT;

	/** 变量区 End**/
	
	protected BaseTransactionShell() {}
	
	@Override
	public Optional<Transaction> characteristic() {
		transactionResults = baseTransactionResultShell.characteristic().get();
		eventsList = new ArrayList<>();
		return Optional.of(this);
	}
	
	/**
	* @name 事件生产，生产者调用
	* @illustrate 事件生产，生产者调用
	* @param transactionAssembleAnalysis 事务生产分析接口
	* **/
	@Override
	public Optional<TransactionAssembleAnalysisHandle> transactionAssemble(Optional<TransactionAssembleAnalysis> transactionAssembleAnalysis){
		if(transactionState != TransactionState.INIT && transactionState != TransactionState.WAIT){
			logMethod.ERROR(this, "Transaction无法构建，因为Transaction状态不为Init（初始状态）或者 Wait（等待状态），现在Transaction的状态为：" + transactionState);
			throw new IllegalStateException("Transaction无法构建，因为Transaction状态不为Init（初始状态）或者 Wait（等待状态），现在Transaction的状态为：" + transactionState);
		}
		else if(transactionState == TransactionState.INIT){
			this.injectSequenceID(Optional.of(SequenceUtil.getUUIDSequence()));
			transactionState = TransactionState.WAIT;
		}
		return transactionAssembleAnalysis.get().analysis(Optional.of(eventsList), Optional.of(parametersCache), transactionResults.resultAssemble(Optional.of(transactionResultsAssembleAnalysis)));
	}

	/**
	 * @name 启动事务，运行者接口
	 * @illustrate 启动事务，运行者接口
	 * @param transactionExecuteAnalysis 事务运行分析接口
	 * **/
	@Override
	public Optional<TransactionExecuteAnalysisHandle> transactionExecute(Optional<TransactionExecuteAnalysis> transactionExecuteAnalysis) {
		TransactionExecuteAnalysisHandle ransactionExecuteAnalysisHandle = transactionExecuteAnalysis.get().analysis(Optional.of(eventsList), Optional.of(parametersCache), transactionResults.resultsExecute(Optional.of(transactionResultsExecuteAnalysis))).get();
		if(TransactionState.WAIT == transactionState){
			transactionState = TransactionState.LINEUP;
		}
		else if(TransactionState.PASS == transactionState){
			logMethod.INFO(this, "事务处于PASS状态，将快速失败此事务");
		}
		else{
			logMethod.ERROR(this, "Transaction无法启动，因为Transaction无法启动状态不为Wait（等待执行状态），现在Transaction无法启动的状态为：" + transactionState);
			throw new IllegalStateException("Transaction无法启动，因为Transaction无法启动状态不为Wait（等待执行状态），现在Transaction无法启动的状态为：" + transactionState);
		}
		return Optional.of(ransactionExecuteAnalysisHandle);
	}

	/**
	* @name 事务结果分析，分析者接口
	* @illustrate 事务结果分析，分析者接口
	* @param transactionResultAnalysisArg 事务结果分析接口
	* **/
	@Override
	public Optional<TransactionResultAnalysisHandle> transactionResult(Optional<TransactionResultAnalysis> transactionResultAnalysisArg){
		return transactionResultAnalysisArg.get().analysis(Optional.of(parametersCache), Optional.of(eventsList), transactionResults.resultsResult(Optional.of(transactionResultsResultAnalysis)));
	}
	
	/**
	* @name 跳过此事务，通过调用其名下的Event的skipEvent方法来主动放弃Event的执行
	* @illustrate 跳过此事务，通过调用其名下的Event的skipEvent方法来主动放弃Event的执行
	* **/
	@Override
	public void skipTransaction(Optional<String> skipCause) {
		eventsList.forEach(x -> x.skipEvent(skipCause));
	}
	
	@Override
	public String toString() {
		StringBuffer jsonResult = new StringBuffer()
				.append("{")
				.append(JsonUtil.cacheToJson(Optional.of(parametersCache), Optional.of("transactionInteractionCache")))
				.append(",")
				.append(transactionResults.toString())
				.append(",")
				.append(JsonUtil.StringToJson(Optional.of("TransactionState"), Optional.of(transactionState.toString())));

		if(eventsList != null && eventsList.size() >0){
			eventsList.stream().map(event -> event.toString())
					           .forEach(json -> jsonResult.append(json + "\","));
			jsonResult.deleteCharAt(jsonResult.lastIndexOf(","));
		}

		jsonResult.append("}");
		return jsonResult.toString();
	}

	
}
