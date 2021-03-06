package com.stargazerproject.cache.impl.resources;

import com.google.common.base.Optional;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="aggregateRootIndexCacheRemovalListenerCharacteristic")
@Qualifier("aggregateRootIndexCacheRemovalListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AggregateRootIndexCacheRemovalListenerCharacteristic implements BaseCharacteristic<RemovalListener<String, Cache<String, String>>>{

	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;

	private RemovalListener<String, Cache<String, String>> removalListener;

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private AggregateRootIndexCacheRemovalListenerCharacteristic() {}

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootIndexCacheRemovalListenerCharacteristic(Optional<LogMethod> logArg) {
		log = logArg.get();
	}
	
	@Override
	public Optional<RemovalListener<String, Cache<String, String>>> characteristic() {
		initializationRemovalListener();
		return Optional.of(removalListener);
	}
	
	private void initializationRemovalListener(){
		removalListener = new RemovalListener<String, Cache<String, String>>(){
			@Override
			public void onRemoval(RemovalNotification<String, Cache<String, String>> notification) {
				switch (notification.getCause().name()) {
				/**表明键或值被垃圾回收**/
				case "COLLECTED":
					log.WARN(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction Has Been Remove By Garbage Collection");
					break;
					/**表明最近一次写条目或获取条目的时间超时**/
				case "EXPIRED":
					log.WARN(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction Has Been Remove By Timeout");
					break;
					/**表明用户手动的移除条目**/
				case "EXPLICIT":
					log.INFO(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction Has Been Remove By Normal Way");
					break;
					/**表明条目不是真正的被移除，只是value值被改变**/
				case "REPLACED":
					log.INFO(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction Value Has Been Change");
					break;
					/**表明由于Cache的长度达到或接近设置的最大限制，条目被移除**/
				case "SIZE":
					log.WARN(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction Has Been Remove By Reached The Maximum Queue Length");
					break;
				default:
					log.ERROR(AggregateRootIndexCacheRemovalListenerCharacteristic.class, "Transaction : An Unknown Error");
					break;
				}
			}	
		};
	}

}
