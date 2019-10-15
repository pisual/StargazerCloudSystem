package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionAssembleAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value="transactionAssembleAnalysisShell")
@Qualifier("transactionAssembleAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionAssembleAnalysisShell implements TransactionAssembleAnalysis, BaseCharacteristic<TransactionAssembleAnalysis> {

    @Override
    public Optional<TransactionAssembleAnalysisHandle> analysis(Optional<Collection<Event>> eventList, Optional<Cache<String, String>> interactionCacheArg, Optional<TransactionResultsAssembleAnalysisHandle> transactionResultsAssembleAnalysisHandleArg) {
        return Optional.of(new TransactionAssembleAnalysisHandleResources(eventList, interactionCacheArg, transactionResultsAssembleAnalysisHandleArg));
    }

    @Override
    public Optional<TransactionAssembleAnalysis> characteristic() {
        return Optional.of(this);
    }

}
