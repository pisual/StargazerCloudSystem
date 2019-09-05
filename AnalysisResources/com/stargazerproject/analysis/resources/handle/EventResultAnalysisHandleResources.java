package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResultState;

import java.util.concurrent.TimeUnit;

public class EventResultAnalysisHandleResources implements EventResultAnalysisHandle {

    private Cache<String, String> resultCache;

    private Cache<String, String> interactionCache;

    public EventResultAnalysisHandleResources(Optional<Cache<String, String>> resultCacheArg, Optional<Cache<String, String>> interactionCacheArg){
        resultCache = resultCacheArg.get();
        interactionCache = interactionCacheArg.get();
    }

    @Override
    public Optional<EventResultState> resultState() {
        String resultState = interactionCache.get(Optional.of("EventResultState")).get();
        return conversionResultState(resultState);
    }

    @Override
    public Optional<TimeUnit> waitTimeoutUnit(){
        String waitTimeoutUnit = interactionCache.get(Optional.of("waitTimeoutUnit")).get();
        switch (waitTimeoutUnit){
            case "MICROSECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "MILLISECONDS":
                return Optional.of(TimeUnit.MICROSECONDS);
            case "SECONDS":
                return Optional.of(TimeUnit.SECONDS);
            default:
                throw new NullPointerException("waitTimeoutUnit Error");
        }
    }

    @Override
    public Optional<Integer> waitTimeout(){
        String waitTimeout = interactionCache.get(Optional.of("waitTimeout")).get();
        return Optional.of(Integer.parseInt(waitTimeout));
    }

    private Optional<String> method(){
        return interactionCache.get(Optional.of("Method"));
    }

    private Optional<EventResultState> conversionResultState(String result){
        EventResultState resultState;
        switch (result){
            case "SUCCESS":
                resultState = EventResultState.SUCCESS;
            break;
            case "FAULT":
                resultState = EventResultState.FAULT;
                break;
            case "WAIT":
                resultState = EventResultState.WAIT;
                break;
            default:
                throw new NullPointerException("EventResultState Error , EventResultState : " + result);
        }
        return Optional.of(resultState);
    }

}
