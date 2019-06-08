package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    private Cache<String, String> resultCache;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<Cache<String, String>> resultCacheArg){
        cache = cacheArg.get();
        resultCache = resultCacheArg.get();
    }

    @Override
    public void run() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
//        System.out.println("###############        :   "+cellsTransaction.eventAnnotation().);
        cellsTransaction.method(Optional.of(cache), Optional.of(resultCache));
    }

    private Optional<String> method(){
        return cache.get(Optional.of("Method"));
    }
}
