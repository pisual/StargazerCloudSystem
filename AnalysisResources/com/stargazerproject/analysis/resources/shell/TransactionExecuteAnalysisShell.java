package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionExecuteAnalysisHandleResoources;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.queue.QueueProducer;
import com.stargazerproject.transaction.EventExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class TransactionExecuteAnalysisShell implements TransactionExecuteAnalysis, BaseCharacteristic<TransactionExecuteAnalysis> {

    @Autowired
    @Qualifier("eventExecuteQueue")
    public QueueProducer<EventExecute> eventExecuteQueue;

    @Override
    public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<EventExecute>> eventList) {
        eventList.get().forEach(event -> eventExecuteQueue.producer(Optional.of(event)));
        return Optional.of(new TransactionExecuteAnalysisHandleResoources());
    }

    @Override
    public Optional<TransactionExecuteAnalysis> characteristic() {
        return null;
    }
}
