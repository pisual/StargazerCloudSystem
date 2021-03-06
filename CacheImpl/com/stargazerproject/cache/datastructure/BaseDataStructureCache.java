package com.stargazerproject.cache.datastructure;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

/** 
 *  @name Event内部交换缓存（eventI nteraction Cache）
 *  @illustrate Event内部交换缓存，针对每一个Event提供的提供独立的内部交换缓存
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component(value="baseDataStructureCache")
@Qualifier("baseDataStructureCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseDataStructureCache<K, V> implements Cache<K, V>, BaseCharacteristic<Cache<K, V>> {
		
	private static final long serialVersionUID = 4406535394386240817L;
	
	protected Map<K, V> cache = new ConcurrentSkipListMap<K, V>();

	/** @construction 初始化构造 **/
	public BaseDataStructureCache() {}

	@Override
	public Optional<Cache<K, V>> characteristic() {
		return Optional.of(this);
	}

	/**
	 * @name 置入
	 * @illustrate 缓存内容置入,Key及Value均不允许空值
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @param @Optional <V> Guava包装缓存的Value值，不允许空值
	 * @ThreadSafeMethodsLevel put方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public void put(Optional<K> key, Optional<V> value) {
		cache.put(key.get(), value.get());
	}

	/**
	 * @name 获取
	 * @illustrate 缓存内容获取
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <V> Guava包装缓存的Value值，如果Key值没有对应的Value，则返回Optional的空值包装模式
	 * @ThreadSafeMethodsLevel get方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@Override
	public Optional<V> get(Optional<K> key) {
		return Optional.fromNullable(cache.get(key.get()));
	}

	/**
	 * @name 移除
	 * @illustrate 移除缓存内容
	 * @param @Optional <K> Guava包装缓存的Key值，不允许空值
	 * @return @Optional <V> Guava包装缓存的Boolean值，成功删除返回True，删除失败（没有相应的Key值条数）这返回False
	 * @ThreadSafeMethodsLevel remove方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	@Override
	public Optional<Boolean> remove(Optional<K> key) {
		return (null == cache.remove(key.get()))?Optional.of(Boolean.FALSE):Optional.of(Boolean.TRUE);
	}
	
	/**
	 * @name 清除
	 * @illustrate 清除缓存所有内容
	 * @ThreadSafeMethodsLevel remove方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public void clear(){
		cache.clear(); 
	}
	
	/**
	 * @name 获取结果集
	 * @illustrate 获取Set类型的结果集，结果集是有序的（自然排序）
	 * @ThreadSafeMethodsLevel entrySet方法的线程安全级别是 ThreadSafeLevel.ThreadSafe，安全的线程安全方法
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<Set<Entry<K, V>>> entrySet(){
		return ( Optional.of(cache.entrySet()) );
	}
}