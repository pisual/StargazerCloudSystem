package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.cell.method.exception.RunException;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.util.SerializableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/** 
 *  @name 注入参数
 *  @illustrate 根据获取到的参数注入到系统参数缓存
 *  @author Felixerio
 *  **/
@Component(value="injectParameterModel")
@Qualifier("injectParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InjectParameterModel extends CellsTransactionImpl {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;

	@Autowired
	@Qualifier("byteArrayCache")
	protected BigCache<String, byte[]> byteArrayCache;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameterCahce;
	
	public InjectParameterModel() { 
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
	@HystrixCommand(commandKey = "injectParameterModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="injectParameterModel", 
	                threadPoolKey = "injectParameterModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle) {
		try {
			Optional<Object> parameterClass = getParameterClass();
			Optional<Map<String, String>> paramentMap= getParamentMap(parameterClass);
			injectParameter(paramentMap);
			success();
		} catch (Exception e) {
			throw new RunException(e.getMessage());
		}
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional <Cache<String, String>> cache
	* @param : Throwable throwable
	* **/
	@Override
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle, Throwable throwable){
		super.fallBack(interactionCache, eventResultsExecuteAnalysisHandle, throwable);
	}
	/**
	 * @illustrate 获取参数类
	 * **/
	private Optional<Object> getParameterClass(){
		byte[] parameterClassByte = byteArrayCache.get(Optional.of("AcquireParameterModel")).get();
		Object object = SerializableUtil.deserialize(parameterClassByte);
		return Optional.of(object);
	}
	
	/**
	 * @illustrate 从Object获取参数并注入到Map列表
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * **/
	private Optional<Map<String, String>> getParamentMap(Optional<Object> object) throws IllegalArgumentException, IllegalAccessException{
		Map<String, String> paramentMap = new HashMap<String, String>();
		Field[] valueFields = object.getClass().getDeclaredFields();
		for(Field valueField : valueFields){
			valueField.setAccessible(true);
			paramentMap.put(valueField.getName(), valueField.get(valueField.getName()).toString());
			log.DEBUG(this, "Afresh Load Parameters ，Key : " + valueField.getName() + "  Value : " + valueField.get(valueField.getName()).toString());
			}
		return Optional.of(paramentMap);
	}
	
	/**
	 * @illustrate 注入Map里的参数到systemParameterCahce
	 * **/
	private void injectParameter(Optional<Map<String, String>> paramentMap){
		paramentMap.get().forEach((k, v) -> systemParameterCahce.put(Optional.of(k), Optional.of(v)));
	}
	
}
