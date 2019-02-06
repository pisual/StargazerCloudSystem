package com.stargazerproject.queue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;
import com.stargazerproject.transaction.EventExecute;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name Event队列
 *  @illustrate Event队列队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="eventExecuteQueue")
@Qualifier("eventExecuteQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventExecuteQueue extends StandQueue<EventExecute> implements StanderCharacteristicShell<Queue<EventExecute>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected EventExecuteQueue() {}

	@Override
	public void initialize(Optional<Queue<EventExecute>> queueArg) {

		queue = queueArg.get();
	}

}