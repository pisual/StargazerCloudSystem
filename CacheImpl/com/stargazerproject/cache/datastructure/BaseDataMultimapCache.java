package com.stargazerproject.cache.datastructure;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Component(value="baseDataMultimapCache")
@Qualifier("baseDataMultimapCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseDataMultimapCache<K, V> implements MultimapCache<K, V>, BaseCharacteristic<MultimapCache<K, V>> {

    private Multimap<K, V> cache = ArrayListMultimap.create();

    @Override
    public Optional<MultimapCache<K, V>> characteristic() {
        return Optional.of(this);
    }

    @Override
    public void put(Optional<K> key, Optional<V> value) {
        cache.put(key.get(), value.get());
    }

    @Override
    public Optional<Collection<V>> get(Optional<K> key) {
        return Optional.of(cache.get(key.get()));
    }

    @Override
    public void remove(Optional<K> key) {
        cache.removeAll(key.get());
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Optional<Set<Map.Entry<K, Collection<V>>>> entrySet() {
        return Optional.of(cache.asMap().entrySet());
    }

}
