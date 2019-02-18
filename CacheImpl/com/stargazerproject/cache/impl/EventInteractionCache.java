package com.stargazerproject.cache.impl;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventInteractionCache")
@Qualifier("eventInteractionCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NeedInitialization(content = "{\"ResultState\" : \"WAIT\"," /** @illustrate 结果状态（"Faile" Or "Success"） **/
							+ " \"ErrorMessage\" : \"NULL\","/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"waitTimeoutUnit\" : \"MILLISECONDS\","/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"waitTimeout\" : \"500\","/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"runTimeoutUnit\" : \"MILLISECONDS\","/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"runTimeout\" : \"500\","/** @illustrate 异常信息（NULL Or ExceptionMessage） **/
							+ " \"Method\" : \"NULL\"}")      /** @illustrate 完成时间（0 Or 格林威治时间（精确到微秒）） **/
@NoSpringDepend
public final class EventInteractionCache extends BaseCacheImpl<String, String> implements StanderCharacteristicShell<Cache<String, String>> {

	private static final long serialVersionUID = 7649254860731557694L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventInteractionCache() {}

	@Override
	public void initialize(Optional<Cache<String, String>> cacheArg) {
		cache = cacheArg.get();
	}
}
