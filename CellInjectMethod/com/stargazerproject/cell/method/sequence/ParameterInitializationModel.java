package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.cell.method.exception.RunException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="parameterInitializationModel")
@Qualifier("parameterInitializationModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ParameterInitializationModel extends CellsTransactionImpl {
	
	/** @name 新生区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_EdenNodePath;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("negotiateParametersInjectInitializationListenerCharacteristic")
	private BaseCharacteristic<TreeCacheListener> negotiateParametersInjectInitializationListenerCharacteristic;
	
	public ParameterInitializationModel() { 
		super(); 
		}

	public Event eventAnnotation(){
		return this.getClass().getAnnotation(Event.class);
	}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "parameterInitializationModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="parameterInitializationModel", 
	                threadPoolKey = "parameterInitializationModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle) {
		try {
			nodeNegotiate.registeredWatcher(Optional.of(""), Optional.of(Kernel_Negotiate_BasePath_EdenNodePath), Optional.of("parameterInitializationModel"), negotiateParametersInjectInitializationListenerCharacteristic.characteristic());
			success();
		} catch (Exception e) {
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
