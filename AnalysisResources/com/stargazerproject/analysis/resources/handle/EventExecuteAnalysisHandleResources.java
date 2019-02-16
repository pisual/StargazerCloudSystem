package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.EventTimeOut;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg){
        cache = cacheArg.get();
    }

    @Override
    public void run() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
        cellsTransaction.method(Optional.of(cache));
    }


    @Override
    public Optional<EventTimeOut> eventEventTimeOutConfiguration() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
        return Optional.of(cellsTransaction.getClass().getAnnotation(EventTimeOut.class));
    }

    private Optional<String> method(){
        return cache.get(Optional.of("Method"));
    }
}
