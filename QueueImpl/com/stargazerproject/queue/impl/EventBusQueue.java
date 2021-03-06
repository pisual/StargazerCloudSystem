package com.stargazerproject.queue.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name EventBus队列
 *  @illustrate EventBus队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="eventBusQueue")
@Qualifier("eventBusQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusQueue extends StandQueue<Event> implements StanderCharacteristicShell<Queue<Event>>{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	protected EventBusQueue() {}

	@Override
	public void initialize(Optional<Queue<Event>> queueArg) {
		queue = queueArg.get();
	}

}