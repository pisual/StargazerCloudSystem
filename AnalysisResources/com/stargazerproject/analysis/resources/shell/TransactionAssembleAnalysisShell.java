package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionAssembleAnalysisHandleResources;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public class TransactionAssembleAnalysisShell implements TransactionAssembleAnalysis, BaseCharacteristic<TransactionAssembleAnalysis> {

    @Override
    public Optional<TransactionAssembleAnalysisHandle> analysis(Optional<Collection<Event>> eventList) {
        return Optional.of(new TransactionAssembleAnalysisHandleResources(eventList));
    }

    @Override
    public Optional<TransactionAssembleAnalysis> characteristic() {
        return Optional.of(this);
    }
}
