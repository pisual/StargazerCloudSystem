package com.stargazerproject.queue.resources.shell;

import com.google.common.base.Optional;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.model.EventQueueEvent;
import com.stargazerproject.queue.resources.BaseQueueRingBuffer;
import com.stargazerproject.queue.resources.impl.EventBusHandler;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Component(value="eventBusDisruptorShell")
@Qualifier("eventBusDisruptorShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusDisruptorShell extends BaseQueueRingBuffer<Event, EventQueueEvent> implements BaseCharacteristic<Queue<Event>>{
	
	/** @name 接收EventBus队列的缓存数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_ReceiveEventBusQueue_Memory_BufferSize;
	
	/** @name 接收EventBus队列的消费者数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Queue_ReceiveEventBusQueue_Consumer_NumberOfConsumers;
	
	@Autowired
	@Qualifier("eventFactory")
	private EventFactory<EventQueueEvent> eventFactory;
	
	@Autowired
	@Qualifier("eventQueueThreadFactory")
	private ThreadFactory threadFactory;
	
	@Autowired
	@Qualifier("cleanEventHandler")
	private WorkHandler<EventQueueEvent> cleanEventHandler;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private EventBusDisruptorShell() {
		super.translator = new EventTranslatorOneArg<EventQueueEvent, Event>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, Event event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventBusDisruptorShell(Optional<ThreadFactory> threadFactoryArg, Optional<EventFactory<EventQueueEvent>> eventFactoryArg, Optional<WorkHandler<EventQueueEvent>> cleanEventHandlerArg) {
		eventFactory = eventFactoryArg.get();
		threadFactory = threadFactoryArg.get();
		cleanEventHandler = cleanEventHandlerArg.get();
		
		super.translator = new EventTranslatorOneArg<EventQueueEvent, Event>() {
			public void translateTo(EventQueueEvent eventQueueEvent, long sequence, Event event) {
				eventQueueEvent.setEvent(event);
			}
		};
	}
	
	@Override
	public Optional<Queue<Event>> characteristic() {
		handleEvents();
		disruptorInitialization();
		return Optional.of(this);
	}
	
	private void disruptorInitialization(){
		disruptor = new Disruptor<EventQueueEvent>(eventFactory, getIntegerParameter(Kernel_Queue_ReceiveEventBusQueue_Memory_BufferSize), Executors.defaultThreadFactory(), ProducerType.SINGLE, new PhasedBackoffWaitStrategy(1,2,TimeUnit.SECONDS,new BlockingWaitStrategy()));
	//	disruptor.setDefaultExceptionHandler(new EventOutTimeExceptionHandler<EventQueueEvent>());
		disruptor.handleEventsWithWorkerPool(handler).thenHandleEventsWithWorkerPool(cleanEventHandler);
	}
	
	private void handleEvents(){
		handler = new EventBusHandler[getIntegerParameter(Kernel_Queue_ReceiveEventBusQueue_Consumer_NumberOfConsumers)];
		for(int i=0; i<getIntegerParameter(Kernel_Queue_ReceiveEventBusQueue_Consumer_NumberOfConsumers); i++){
			handler[i] = BeanContainer.instance().getBean(Optional.of("eventBusHandler"), WorkHandler.class);
		}
	}
	
	private Integer getIntegerParameter(String value){
		return Integer.parseInt(value);
	}
	
}