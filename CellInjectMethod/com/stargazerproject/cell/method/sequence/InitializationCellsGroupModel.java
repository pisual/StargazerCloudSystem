package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.stargazerproject.analysis.handle.EventResultRecordAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="initializationCellsGroupModel")
@Qualifier("initializationCellsGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Event()
public class InitializationCellsGroupModel extends CellsTransactionImpl {
	
	public InitializationCellsGroupModel() { 
		super(); 
		}

	public Event eventAnnotation(){
		return this.getClass().getAnnotation(Event.class);
	}

	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache 参数缓存
	* **/
	@Override
	@HystrixCommand(commandKey = "initializationCellsGroupModel", 
	                fallbackMethod = "fallBack",
	                groupKey="initializationCellsGroupModel", 
	                threadPoolKey = "initializationCellsGroupModel",
			        ignoreExceptions = HystrixRuntimeException.class,
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	@Event()
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultRecordAnalysisHandle> eventResultRecordAnalysisHandle) {
		super.method(interactionCache, eventResultRecordAnalysisHandle);
		eventResultRecordAnalysisHandle.get().putAggregationRootCache(Optional.of("OrderID"), Optional.of(SequenceUtil.getUUID()));
		log.INFO(this,"initializationCellsGroupModel Complete , OrderID : " + SequenceUtil.getUUID());
		log.INFO(this,"initializationCellsGroupModel Complete , User: " + interactionCache.get().get(Optional.of("User")).get());
		success();
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* @param : Throwable throwable
	* **/
	@Override
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultRecordAnalysisHandle> eventResultRecordAnalysisHandle, Throwable throwable){
		super.fallBack(interactionCache, eventResultRecordAnalysisHandle, throwable);
	}
}
