package com.stargazerproject.cache;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MultimapCache<K, V> extends Serializable {
    /**
     * @name 置入
     * @illustrate 缓存内容置入,Key及Value均不允许空值，如果置入的元素已经存在，则进行覆盖
     * @param @Optional <K> Guava包装缓存的Key值，不允许空值
     * @param @Optional <V> Guava包装缓存的Value值，不允许空值
     * @ThreadSafeMethodsLevel 依赖具体的实现方法
     * **/
    @ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
    public void put(Optional<K> key, Optional<V> value);

    /**
     * @name 获取
     * @illustrate 缓存内容获取
     * @param @Optional <K> Guava包装缓存的Key值，不允许空值
     * @return @Optional <V> Guava包装缓存的Collection<Value></Value>值，如果Key值没有对应的Value，则返回Optional的空值包装模式
     * @ThreadSafeMethodsLevel 依赖具体的实现方法
     * **/
    @ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
    public Optional<Collection<V>> get(Optional<K> key);

    /**
     * @name 移除
     * @illustrate 移除缓存内容
     * @param @Optional <K> Guava包装缓存的Key值，不允许空值
     * @return @Optional <V> Guava包装缓存的Boolean值，成功删除返回True，删除失败（没有相应的Key值条数）这返回False
     * @ThreadSafeMethodsLevel 依赖具体的实现方法
     * **/
    @ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
    public void remove(Optional<K> key);

    /**
     * @name 清除
     * @illustrate 清除缓存所有内容
     * @ThreadSafeMethodsLevel 依赖具体的实现方法
     * **/
    @ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
    public void clear();

    /**
     * @name 获取结果集
     * @illustrate 获取Set类型的结果集，结果集是的排序依赖具体实现方法
     * @ThreadSafeMethodsLevel 依赖具体的实现方法
     * **/
    @ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.Implementation)
    public Optional<Set<Map.Entry<K, Collection<V>>>> entrySet();
}
