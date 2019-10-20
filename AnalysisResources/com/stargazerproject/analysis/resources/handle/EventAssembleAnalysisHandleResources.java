package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsAssembleAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.date.EventDate;

import java.util.concurrent.TimeUnit;

public class EventAssembleAnalysisHandleResources implements EventAssembleAnalysisHandle {

    private Cache<String, String> cacheAssemble;

    private EventResultsAssembleAnalysisHandle eventResultsAssembleAnalysisHandle;

    public EventAssembleAnalysisHandleResources(Optional<Cache<String, String>> cacheAssembleArg, Optional<EventResultsAssembleAnalysisHandle> eventResultsAssembleAnalysisHandleArg){
        cacheAssemble = cacheAssembleArg.get();
        eventResultsAssembleAnalysisHandle = eventResultsAssembleAnalysisHandleArg.get();
    }

    @Override
    public EventAssembleAnalysisHandle injectEventParameter(Optional<String> Key, Optional<String> value) {
        cacheAssemble.put(Key, value);
        return this;
    }

    @Override
    public void injecrParametersFromJson(Optional<String> json){
        LinkedTreeMap map = new Gson().fromJson(json.get(), LinkedTreeMap.class);
        map.forEach((key, value) -> {
            if(EventDate.eventInteractionCache.toString().equals(key.toString())){
                LinkedTreeMap eventInteractionCacheLinkedTreeMap = (LinkedTreeMap) value;
                eventInteractionCacheLinkedTreeMap.forEach((eKey,eValue) -> {
                cacheAssemble.put(Optional.of(eKey.toString()), Optional.of(eValue.toString()));
                });
            }
            else if(EventDate.eventResultCache.toString().equals(key.toString())){
                LinkedTreeMap eventResultCacheLinkedTreeMap = (LinkedTreeMap) value;
                eventResultCacheLinkedTreeMap.forEach((erKey, erValue) -> {
                eventResultsAssembleAnalysisHandle.injectEventResultsParameter(Optional.of(erKey.toString()), Optional.of(erValue.toString()));
                });
            }
        });
    }

    @Override
    public Optional<Integer> getEventTimeOut() {
        Optional<Integer> eventTimeOut = stringToInteger(cacheAssemble.get(Optional.of(EventDate.WaitTimeout.toString())));
        return eventTimeOut;
    }

    @Override
    public Optional<TimeUnit> getEventTimeOutTimeUnit() {
        Optional<String> eventTimeOutTimeUnit = cacheAssemble.get(Optional.of(EventDate.WaitTimeoutUnit.toString()));
        return stringToTimeUnit(eventTimeOutTimeUnit);
    }

    /**
     * @name 字符串转化为数字
     * @illustrate 字符串转化为数字
     * @param Optional<String> eventTimeOut， 输入的字符串
     * @Return Optional<Integer> 转化为Integer之后的结果
     * **/
    private Optional<Integer> stringToInteger(Optional<String> eventTimeOut){
        return Optional.of(Integer.valueOf(eventTimeOut.get()));
    }

    /**
     * @name eventTimeOutTimeUnit字符串转化为TimeUnit格式
     * @illustrate eventTimeOutTimeUnit字符串转化为TimeUnit格式，通过匹配TimeUnit的内部枚举名称
     * @param Optional<String> eventTimeOutTimeUnitArg， 输入的字符串，需要匹配TimeUnit内部枚举
     * @Return Optional<TimeUnit> 转换过后的TimeUnit
     * **/
    private Optional<TimeUnit> stringToTimeUnit(Optional<String> eventTimeOutTimeUnitArg){
        String eventTimeOutTimeUnit = eventTimeOutTimeUnitArg.get();
        TimeUnit timeUnit;
        switch (eventTimeOutTimeUnit){
            case "NANOSECONDS":
                timeUnit =  TimeUnit.NANOSECONDS;
                break;
            case "MICROSECONDS":
                timeUnit =  TimeUnit.MICROSECONDS;
                break;
            case "MILLISECONDS":
                timeUnit =  TimeUnit.MILLISECONDS;
                break;
            case "SECONDS":
                timeUnit =  TimeUnit.SECONDS;
                break;
            case "MINUTES":
                timeUnit =  TimeUnit.MINUTES;
                break;
            case "DAYS":
                timeUnit =  TimeUnit.DAYS;
                break;
            default:
                throw new IllegalArgumentException("eventTimeOutTimeUnit Invalid format，eventTimeOutTimeUnit is " + eventTimeOutTimeUnit);

        }
        return Optional.of(timeUnit);
    }
}
