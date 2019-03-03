package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.cache.MultimapCache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class MultimapCacheImpl<K, V> implements MultimapCache<K, V> {

    protected MultimapCache multimapCache;

    @Override
    public void put(Optional<K> key, Optional<V> value) {
        multimapCache.put(key, value);
    }

    @Override
    public Optional<Collection<V>> get(Optional<K> key) {
        return multimapCache.get(key);
    }

    @Override
    public void remove(Optional<K> key) {
        multimapCache.remove(key);
    }

    @Override
    public void clear() {
        multimapCache.clear();
    }

    @Override
    public Optional<Set<Map.Entry<K, Collection<V>>>> entrySet() {
        return multimapCache.entrySet();
    }

    public void initiationParameters(Class clazz) {
        NeedInitialization needInitialization = (NeedInitialization) clazz.getAnnotation(NeedInitialization.class);
        initMultimapParameters(parametersMap(needInitialization.content()), multimapCache);
    }

    private Map<String, String> parametersMap(String content){
        Map<String, String> map = new Gson().fromJson(content, Map.class);
        return map;
    }

    private void initMultimapParameters(Map<String, String> map, MultimapCache multimapCache){
        map.entrySet().stream().forEach(parameter -> multimapCache.put(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
    }
}
