package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.StandardCellsTransactionImpl;
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
public class Test_NowTimeModel extends StandardCellsTransactionImpl {

	public Test_NowTimeModel() {
		super(); 
		}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache 参数缓存
	* **/
	@Override
	@HystrixCommand(commandKey = "test_GetCellsGroupIDModel",
	                fallbackMethod = "fallBack",
	                groupKey="test_GetCellsGroupIDModel",
	                threadPoolKey = "test_GetCellsGroupIDModel",
			        ignoreExceptions = HystrixRuntimeException.class,
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache) {
		log.INFO(this,"Test_NowTimeModel Complete , Time : " + LocalDateTime.now());
		success(interactionCache);
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* @param : Throwable throwable
	* **/
	@Override
	public void fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		super.fallBack(cache, throwable);
    }
	
}
