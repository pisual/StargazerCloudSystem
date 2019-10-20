package com.stargazerproject.cache.impl;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventInteractionCache")
@Qualifier("eventInteractionCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NeedInitialization(content = "{\"EventResultState\" : \"WAIT\"," 			/** @illustrate 结果状态（"Faile" Or "Success"） **/
							+ " \"ErrorMessage\" : \"NULL\","		 	/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"WaitTimeoutUnit\" : \"MILLISECONDS\","/** @illustrate 等待超时时间单位（TimeUnit类型，默认为MILLISECONDS） **/
							+ " \"WaitTimeout\" : \"500\","				/** @illustrate 等待超时时间（500 Or 其他数值） **/
							+ " \"RunTimeoutUnit\" : \"MILLISECONDS\","	/** @illustrate 运行超时时间单位（TimeUnit类型，默认为MILLISECONDS **/
							+ " \"RunTimeout\" : \"500\","				/** @illustrate 运行超时时间（500 Or 其他数值） **/
						    + " \"AggregateRoot\" : \"NULL\","			/** @illustrate 聚合跟ID，NULL或者具体的ID **/
		     			    + " \"Method\" : \"NULL\"}")      			/** @illustrate 调动方法（NULL Or 具体的方法） **/
@NoSpringDepend
public final class EventInteractionCache extends BaseCacheImpl<String, String> implements StanderCharacteristicShell<Cache<String, String>> , BeforehandCharacteristicShell<Cache<String, String>> {

	private static final long serialVersionUID = 7649254860731557694L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventInteractionCache() {
	}

	@Override
	public void initialize(Optional<Cache<String, String>> cacheArg) {
		cache = cacheArg.get();
		initiationParameters(this.getClass());
	}

	@Override
	@Qualifier("baseDataStructureCache")
	@Autowired
	public void initialize(BaseCharacteristic<Cache<String, String>> cacheArg) {
		cache = cacheArg.characteristic().get();
		initiationParameters(this.getClass());
	}

}
