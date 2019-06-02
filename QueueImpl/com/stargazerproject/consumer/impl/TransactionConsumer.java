package com.stargazerproject.consumer.impl;

import com.google.common.base.Optional;
import com.stargazerproject.queue.QueueConsumer;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionConsumer")
@Qualifier("transactionConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionConsumer implements QueueConsumer<Transaction>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public TransactionConsumer() {}
	
	@Override
	public void consumer(Optional<Transaction> transaction) {
		System.out.println("Transaction已经放置到输出队列 " + transaction.get().toString());
	}

}
