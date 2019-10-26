package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.log.LogMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="test_NowTimeModel")
@Qualifier("test_NowTimeModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Event()
public class Test_NowTimeModel extends CellsTransactionImpl {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	public Test_NowTimeModel() {
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
	@HystrixCommand(commandKey = "test_NowTimeModel",
	                fallbackMethod = "fallBack",
	                groupKey="test_NowTimeModel",
	                threadPoolKey = "test_NowTimeModel",
			        ignoreExceptions = HystrixRuntimeException.class,
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle) {
		super.method(interactionCache, eventResultsExecuteAnalysisHandle);
		log.INFO("test_NowTimeModel ","Test_NowTimeModel Complete , Time : " + LocalDateTime.now());
		success();
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
