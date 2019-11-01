package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.EventResultState;
import com.stargazerproject.transaction.date.EventDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Instant;

public class EventResultsExecuteAnalysisHandleResources implements EventResultsExecuteAnalysisHandle {

    /** @illustrate 获取Log(日志)接口 **/
    @Autowired
    @Qualifier("logRecord")
    protected LogMethod log;

    private Cache<String, String> resultCache;

    public EventResultsExecuteAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg){
        resultCache = resultCacheArg.get();
    }

    @Override
    public void EventResultState(Optional<EventResultState> eventResultState) {
        setCompleteTime();
        resultCache.put(Optional.of("EventResultState_" + getRetryTime()), Optional.of(eventResultState.get().toString()));

        if(eventResultState.get().equals(EventResultState.FAULT.toString())){
            increaseRetryTime();
        }
    }

    @Override
    public void errorMessage(Optional<Throwable> throwable) {
        String errorMessage = throwable.get().toString();
        resultCache.put(Optional.of("ErrorMessage_" + getRetryTime()), Optional.of(errorMessage));
    }

    @Override
    public void resultMessage(Optional<String> key, Optional<String> message) {
        resultCache.put(Optional.of("ResultMessage_" + getRetryTime() + "_" + key), message);
    }


    private void setCompleteTime(){
        Instant timestamp = Instant.now();
        resultCache.put(Optional.of("CompleteTime_" + getRetryTime()), Optional.of(timestamp.toString()));
    }

    private int getRetryTime(){
        return Integer.parseInt(resultCache.get(Optional.of(EventDate.RetryTime.toString())).get());
    }

    private void increaseRetryTime(){
        int newRetryTime = getRetryTime() + 1;
        resultCache.put(Optional.of(EventDate.RetryTime.toString()), Optional.of(newRetryTime + ""));
    }
}