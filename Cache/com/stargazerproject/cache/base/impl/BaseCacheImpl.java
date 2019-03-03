package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.cache.Cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class BaseCacheImpl<K, V> implements Cache<K, V> {
	
	private static final long serialVersionUID = -8433617516060406881L;
	
	protected Cache<K, V> cache;
	
	protected BaseCacheImpl() {}

	@Override
	public void put(Optional<K> key, Optional<V> value) {
		cache.put(key, value);
	}

	@Override
	public Optional<V> get(Optional<K> key) {
		return cache.get(key);
	}

	@Override
	public Optional<Boolean> remove(Optional<K> key) {
		return cache.remove(key);
	}
	
	@Override
	public void clear() {
		cache.clear();
	}
	
	@Override
	public Optional<Set<Entry<K, V>>> entrySet() {
		return ( cache.entrySet() );
	}

	public void initiationParameters(Class clazz) {
		NeedInitialization needInitialization = (NeedInitialization) clazz.getAnnotation(NeedInitialization.class);
		initMapParameters(parametersMap(needInitialization.content()), cache);initMapParameters(parametersMap(needInitialization.content()), cache);
	}

	private Map<String, String> parametersMap(String content){
		Map<String, String> map = new Gson().fromJson(content, Map.class);
		return map;
	}

	private void initMapParameters(Map<String, String> map, Cache cache){
		map.entrySet().stream().forEach(parameter -> cache.put(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
	}

}