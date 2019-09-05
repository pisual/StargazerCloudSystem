package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultRecordAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

/**
 *  @name Event Execute Analysis Handle的具体实现
 *  @illustrate Event Execute Analysis Handle的具体实现
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public class EventExecuteAnalysisHandleResources implements EventExecuteAnalysisHandle {

    private Cache<String, String> cache;

    private EventResultRecordAnalysisHandle eventResultRecordAnalysisHandle;

    public EventExecuteAnalysisHandleResources(Optional<Cache<String, String>> cacheArg, Optional<EventResultRecordAnalysisHandle> eventResultRecordAnalysisHandleArg){
        cache = cacheArg.get();
        eventResultRecordAnalysisHandle = eventResultRecordAnalysisHandleArg.get();
    }

    /**
     * @name 事件（Event）具体运行方法
     * @illustrate 事件具体运行方法
     * **/
    @Override
    public void run() {
        CellsTransaction cellsTransaction = BeanContainer.instance().getBean(method(), CellsTransaction.class);
        cellsTransaction.method(Optional.of(cache), Optional.of(eventResultRecordAnalysisHandle));
    }

    /**
     * @name 从参数缓存中获取Method（方法）名称
     * @illustrate 从参数缓存中获取Method（方法）名称
     * **/
    private Optional<String> method(){
        return cache.get(Optional.of("Method"));
    }
}
