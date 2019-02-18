package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

import java.util.concurrent.TimeUnit;

public interface EventResultAnalysisHandle extends ResultAnalysisHandle{
    public Optional<TimeUnit> waitTimeoutUnit();
    public Optional<Integer> waitTimeout();
}
