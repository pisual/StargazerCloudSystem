package com.stargazerproject.consumer.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.queue.QueueConsumer;
import com.stargazerproject.transaction.EventExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventExecuteConsumer")
@Qualifier("eventExecuteConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public class EventExecuteConsumer implements QueueConsumer<EventExecute>{

	@Autowired
	@Qualifier("eventExecuteAnalysisImpl")
	private EventExecuteAnalysis eventExecuteAnalysis;

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	protected EventExecuteConsumer() {}

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventExecuteConsumer(Optional<EventExecuteAnalysis> eventExecuteAnalysisArg) {
		eventExecuteAnalysis = eventExecuteAnalysisArg.get();
	}
	
	@Override
	public void consumer(Optional<EventExecute> eventExecute) {
		eventExecute.get().eventExecute(Optional.of(eventExecuteAnalysis));
	}

}
