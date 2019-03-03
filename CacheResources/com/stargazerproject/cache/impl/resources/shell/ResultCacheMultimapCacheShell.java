package com.stargazerproject.cache.impl.resources.shell;

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

@Component(value="resultCacheMultimapCacheShell")
@Qualifier("resultCacheMultimapCacheShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResultCacheMultimapCacheShell implements MultimapCache<String, String>, BaseCharacteristic<MultimapCache<String, String>> {

    private Multimap<String, String> cache = ArrayListMultimap.create();

    @Override
    public Optional<MultimapCache<String, String>> characteristic() {
        return Optional.of(this);
    }

    @Override
    public void put(Optional<String> key, Optional<String> value) {
        cache.put(key.get(), value.get());
    }

    @Override
    public Optional<Collection<String>> get(Optional<String> key) {
        return Optional.of(cache.get(key.get()));
    }

    @Override
    public void remove(Optional<String> key) {
        cache.removeAll(key.get());
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Optional<Set<Map.Entry<String, Collection<String>>>> entrySet() {
        return Optional.of(cache.asMap().entrySet());
    }

}
