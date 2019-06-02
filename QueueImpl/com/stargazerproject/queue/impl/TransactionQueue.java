package com.stargazerproject.queue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name Transaction运行队列
 *  @illustrate Transaction运行对流
 *  
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="transactionExportQueue")
@Qualifier("transactionExportQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionQueue extends StandQueue<Transaction> implements StanderCharacteristicShell<Queue<Transaction>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected TransactionQueue() {}

	@Override
	public void initialize(Optional<Queue<Transaction>> queueArg) {
		queue = queueArg.get();
	}

}