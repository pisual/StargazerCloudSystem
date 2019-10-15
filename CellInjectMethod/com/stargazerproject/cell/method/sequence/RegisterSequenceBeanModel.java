package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.cell.method.exception.RunException;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 模拟远程注入Cells Method的过程
 *  @illustrate 模拟远程注入Cells Method的过程
 *  **/
@Component(value="registerSequenceBeanModel")
@Qualifier("registerSequenceBeanModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterSequenceBeanModel extends CellsTransactionImpl {

	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	public RegisterSequenceBeanModel() { super(); }

	public Event eventAnnotation(){
		return this.getClass().getAnnotation(Event.class);
	}
	
	/**
	* @name 熔断器包裹的方法, 模拟远程注入Cells Method的过程
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "registerSequenceBeanModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="registerSequenceBeanModel", 
	                threadPoolKey = "registerSequenceBeanModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle) {
		try{

			//**模拟远程注入 Start**//
			Object initializationCellsGroupModel = new InitializationCellsGroupModel();
			//**模拟远程注入 End**//

			BeanContainer.instance().setBean(Optional.of(initializationCellsGroupModel.getClass()));

			success();
		} catch(Exception e){
			throw new RunException(e.getMessage());
		}

	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* @param : Throwable throwable
	* **/
	@Override
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle, Throwable throwable){
		super.fallBack(interactionCache, eventResultsExecuteAnalysisHandle, throwable);
	}
}
