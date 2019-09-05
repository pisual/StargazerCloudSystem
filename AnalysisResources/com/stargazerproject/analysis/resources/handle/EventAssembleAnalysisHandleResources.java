package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EventAssembleAnalysisHandleResources implements EventAssembleAnalysisHandle {

    private Cache<String, String> cacheAssemble;

    public EventAssembleAnalysisHandleResources(Optional<Cache<String, String>> cacheAssembleArg){
        cacheAssemble = cacheAssembleArg.get();
    }

    @Override
    public EventAssembleAnalysisHandle injectEventParameter(Optional<String> Key, Optional<String> value) {
        cacheAssemble.put(Key, value);
        return this;
    }

    @Override
    public void injecrParametersFromJson(Optional<String> json){
        Map<String, String> map = new Gson().fromJson(json.get(), Map.class);
        map.entrySet().stream().forEach(parameter -> injectEventParameter(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
    }

    @Override
    public Optional<String> eventToJson() {
        return null;
    }

    @Override
    public Optional<Integer> getEventTimeOut() {
        return null;
    }

    @Override
    public Optional<TimeUnit> getEventTimeOutTimeUnit() {
        return null;
    }

}
