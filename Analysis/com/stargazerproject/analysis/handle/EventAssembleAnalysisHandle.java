package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

import java.util.concurrent.TimeUnit;

public interface EventAssembleAnalysisHandle {

    public EventAssembleAnalysisHandle injectEventParameter(Optional<String> Key, Optional<String> value);

    public void injecrParametersFromJson(Optional<String> json);

    public Optional<String> eventToJson();

    public Optional<Integer> getEventTimeOut();

    public Optional<TimeUnit> getEventTimeOutTimeUnit();

}
