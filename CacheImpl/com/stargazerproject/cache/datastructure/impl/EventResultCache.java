package com.stargazerproject.cache.datastructure.impl;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.BaseCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="eventResultCache")
@Qualifier("eventInteractionCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class EventResultCache extends BaseCacheImpl<String, String> implements BeforehandCharacteristicShell<Cache<String, String>> {

	private static final long serialVersionUID = 7649254860731557794L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public EventResultCache() {}

	@Override
	@Autowired
	@Qualifier("baseDataStructureCache")
	public void initialize(BaseCharacteristic<Cache<String, String>> cacheArg) {
		cache = cacheArg.characteristic().get();
	}
}
