package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.Event;

import java.util.concurrent.TimeUnit;

public interface TransactionAssembleAnalysisHandle {

    public void assembleFromJson (Optional<String> jsonData);

    public void addEvent(Optional<Event> event);

    public Optional<Integer> getTransactionTimeOut();

    public Optional<TimeUnit> getTransactionTimeOutTimeUnit();

}
