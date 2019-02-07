package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.EventTimeOut;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.transaction.Result;

public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    private Result record;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<Result> recordArg){
        cache = cacheArg.get();
        record = recordArg.get();
    }

    @Override
    public void run() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(Optional.of("initializationCellsGroupModel"), CellsTransaction.class);
        cellsTransaction.method(Optional.of(cache));
        System.out.println("Event 开始执行");
    }

    @Override
    public Optional<EventTimeOut> eventEventTimeOutConfiguration() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(Optional.of("initializationCellsGroupModel"), CellsTransaction.class);
        return Optional.of(cellsTransaction.getClass().getAnnotation(EventTimeOut.class));
    }
}
