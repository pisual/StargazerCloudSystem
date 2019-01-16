package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.StandardCellsTransactionImpl;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.util.SequenceUtil;
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
@Component(value="initializationCellsGroupModel")
@Qualifier("initializationCellsGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Event()
public class InitializationCellsGroupModel extends StandardCellsTransactionImpl {
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public InitializationCellsGroupModel() { 
		super(); 
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
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public Optional<Cache<String, String>> method(Optional<Cache<String, String>> cache) {
		cacheggregateRootCache.put(Optional.of("OrderID"), Optional.of(SequenceUtil.getUUID()));
		log.INFO(this, "This_Cells_UUID Initialization: " + cache.get().get(Optional.of("OrderID")).get());
		return Optional.of(cacheggregateRootCache);
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* @param Throwable throwable
	* **/
	public Optional<Cache<String, String>> fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		if(null == throwable){
			log.WARN(this, "BaseEvent FallBack : TimeOut");
		}
		else{
			log.WARN(this, "BaseEvent FallBack : " + throwable.getMessage());
		}
		return Optional.of(cacheggregateRootCache);
    }
	
}
