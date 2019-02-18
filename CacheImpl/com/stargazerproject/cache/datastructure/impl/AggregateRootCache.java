package com.stargazerproject.cache.datastructure.impl;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="aggregateRootCache")
@Qualifier("aggregateRootCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class AggregateRootCache extends BaseDataStructureCache<String, String>{

	private static final long serialVersionUID = 7876165181093327466L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootCache() {}


	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <V> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel put方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public void put(Optional<String> key, Optional<String> value) {
		if(checkValueExist(key)){
			throw new IllegalStateException("Value Exist : Key : " + key.get() + " value : " + value.get());
		}
		else{
			super.put(key, value);
		}
	}

	/**
	 * @name 检测Value是否存在
	 * @illustrate 检测Value是否存在
	 * **/
	private boolean checkValueExist(Optional<String> key){
		if(get(key).isPresent()){
			return Boolean.TRUE;
		}
		else{
			return Boolean.FALSE;
		}
	}

}
