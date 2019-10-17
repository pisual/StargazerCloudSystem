package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionExecuteAnalysisHandleResoources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value="transactionExecuteAnalysisShell")
@Qualifier("transactionExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExecuteAnalysisShell implements TransactionExecuteAnalysis, BaseCharacteristic<TransactionExecuteAnalysis> {

    @Override
    public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<Event>> eventList, Optional<Cache<String, String>> transactionInteractionCache, Optional<TransactionResultsExecuteAnalysisHandle> transactionResultsExecuteAnalysisHandle) {
        return Optional.of(new TransactionExecuteAnalysisHandleResoources(eventList, transactionInteractionCache, transactionResultsExecuteAnalysisHandle));
    }

    @Override
    public Optional<TransactionExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }

}
