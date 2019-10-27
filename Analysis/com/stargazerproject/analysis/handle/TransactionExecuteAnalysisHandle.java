package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

import java.util.concurrent.TimeUnit;

public interface TransactionExecuteAnalysisHandle{

    public void startTransaction();

    public Optional<TimeUnit> waitTimeoutUnit();

    public Optional<Integer> waitTimeout();

    public Optional<TimeUnit> runTimeoutUnit();

    public Optional<Integer> runTimeout();

}
