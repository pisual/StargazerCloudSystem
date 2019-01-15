package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.annotation.description.EventConfiguration;
import com.stargazerproject.cache.Cache;

import java.util.Map;

public class EventAssembleAnalysisHandleResources implements EventAssembleAnalysisHandle {

    private Cache<String, String> cacheAssemble;

    public EventAssembleAnalysisHandleResources(Optional<Cache<String, String>> cacheAssembleArg){
        cacheAssemble = cacheAssembleArg.get();
    }

    @Override
    public void injectEventParameter(Optional<String> Key, Optional<String> value) {
        cacheAssemble.put(Key, value);
    }

    @Override
    public void injecrParametersFromJson(Optional<String> json){
        Map<String, String> map = new Gson().fromJson(json.get(), Map.class);
        map.entrySet().stream().forEach(parameter -> injectEventParameter(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
    }

}
