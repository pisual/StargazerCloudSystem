package com.stargazerproject.sequence.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.transaction.Transaction;

public class SequenceObserverImpl implements SequenceObserver {

    private BusObserver busObserver;

    public SequenceObserverImpl(Optional<BusObserver<Transaction, BusTransactionTimeoutException>> busObserverArg){
        busObserver = busObserverArg.get();
    }

    @Override
    public Optional<Boolean> isComplete() {
        return null;
    }

    @Override
    public Optional<Boolean> isRunning() {
        return null;
    }

    @Override
    public Optional<Boolean> hasError() {
        return null;
    }

    @Override
    public Optional<Throwable> getError() {
        return null;
    }
}
