package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.EventResultState;
import com.stargazerproject.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Instant;

public class EventResultsExecuteAnalysisHandleResources implements EventResultsExecuteAnalysisHandle {

    /** @illustrate 获取Log(日志)接口 **/
    @Autowired
    @Qualifier("logRecord")
    protected LogMethod log;

    /** @illustrate 聚合根缓存 **/
    @Autowired
    @Qualifier("aggregateRootCache")
    private Cache<String, String> aggregateRootCache;

    /** @illustrate 聚合根索引缓存，包含所有的聚合根缓存 **/
    @Autowired
    @Qualifier("aggregateRootIndexCache")
    private Cache<String, Cache<String, String>> aggregateRootIndexCache;

    private Cache<String, String> resultCache;

    public EventResultsExecuteAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg, Optional<String> aggregationRootIDArg){
        resultCache = resultCacheArg.get();
        aggregationRootCacheInitialization(aggregationRootIDArg);
    }

    public EventResultsExecuteAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
        aggregationRootCacheInitialization(Optional.of(SequenceUtil.getUUIDSequence()));
    }

    @Override
    public void EventResultState(Optional<EventResultState> eventResultState) {
        increaseRetryTime();
        setCompleteTime();
        resultCache.put(Optional.of("EventResultState_" + getRetryTime()), Optional.of(eventResultState.get().toString()));
    }

    @Override
    public void errorMessage(Optional<Throwable> throwable) {
        resultCache.put(Optional.of("ErrorMessage_" + getRetryTime()), Optional.of(throwable.get().getMessage()));
    }

    @Override
    public void resultMessage(Optional<String> key, Optional<String> message) {
        resultCache.put(Optional.of("resultMessage_" + getRetryTime() + "_" + key), message);
    }

    @Override
    public void putAggregationRootCache(Optional<String> key, Optional<String> value){
        if(null == aggregateRootCache){
            log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
            throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
        }
        else{
            aggregateRootCache.put(key, value);
        }
    }

    @Override
    public Optional<String> getAggregationRootCache(Optional<String> key){
        if(null == aggregateRootCache){
            log.ERROR(this, "aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
            throw new NullPointerException("aggregateRootCache未初始化， 子类Method方法需要继承父类方法{super.method(Optional<Cache<String, String>> interactionCache)}");
        }
        else{
            return aggregateRootCache.get(key);
        }
    }

    /**
     * @name 聚合根初始化
     * @illustrate 聚合根初始化
     * @return Optional<String> AggregationRootID 聚合根，不同的方法通过聚合根缓存共享数据
     * @param : <String> 聚合跟的Value值
     * **/
    private void aggregationRootCacheInitialization(Optional<String> AggregationRootID){
        aggregateRootIndexCache.put(AggregationRootID, Optional.of(aggregateRootCache));
    }


    private void setCompleteTime(){
        Instant timestamp = Instant.now();
        resultCache.put(Optional.of("CompleteTime_" + getRetryTime()), Optional.of(timestamp.toString()));
    }

    private int getRetryTime(){
        return Integer.parseInt(resultCache.get(Optional.of("RetryTime")).get());
    }

    private void increaseRetryTime(){
        int newRetryTime = getRetryTime() + 1;
        resultCache.put(Optional.of("RetryTime"), Optional.of(newRetryTime + ""));
    }
}