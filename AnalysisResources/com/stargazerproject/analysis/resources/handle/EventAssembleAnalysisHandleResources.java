package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;

public class EventAssembleAnalysisHandleResources implements EventAssembleAnalysisHandle {

    private Cache<String, String> cacheAssemble;

    public EventAssembleAnalysisHandleResources(Optional<Cache<String, String>> cacheAssembleArg){
        cacheAssemble = cacheAssembleArg.get();
    }

    @Override
    public void injectEventParameter(Optional<String> Key, Optional<String> value) {
        cacheAssemble.put(Key, value);
    }

}
