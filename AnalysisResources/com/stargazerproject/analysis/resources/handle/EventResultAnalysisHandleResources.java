package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.transaction.ResultState;

import java.util.concurrent.TimeUnit;

public class EventResultAnalysisHandleResources implements EventResultAnalysisHandle {

    private MultimapCache<String, String> resultCache;

    private Cache<String, String> interactionCache;

    public EventResultAnalysisHandleResources(Optional<MultimapCache<String, String>> resultCacheArg, Optional<Cache<String, String>> interactionCacheArg){
        resultCache = resultCacheArg.get();
        interactionCache = interactionCacheArg.get();
    }

    @Override
    public Optional<ResultState> resultState() {
        String resultState = interactionCache.get(Optional.of("ResultState")).get();
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

    private Optional<ResultState> conversionResultState(String result){
        ResultState resultState;
        switch (result){
            case "SUCCESS":
                resultState = ResultState.SUCCESS;
            break;
            case "FAULT":
                resultState = ResultState.FAULT;
                break;
            case "WAIT":
                resultState = ResultState.WAIT;
                break;
            default:
                throw new NullPointerException("ResultState Error , ResultState : " + result);
        }
        return Optional.of(resultState);
    }

}
