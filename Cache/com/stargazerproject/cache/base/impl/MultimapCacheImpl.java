package com.stargazerproject.cache.base.impl;

import com.google.common.base.Optional;
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
}
