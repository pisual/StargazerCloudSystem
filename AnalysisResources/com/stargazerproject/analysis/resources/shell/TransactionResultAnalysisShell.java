package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultAnalysisHandleResources;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value="transactionResultAnalysisShell")
@Qualifier("transactionResultAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultAnalysisShell implements TransactionResultAnalysis, BaseCharacteristic<TransactionResultAnalysis> {

    @Override
    public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> parametersCache, Optional<Collection<Event>> eventList,  Optional<TransactionResultsResultAnalysisHandle> transactionResultsResultAnalysisHandle) {
        return Optional.of(new TransactionResultAnalysisHandleResources(eventList, parametersCache, transactionResultsResultAnalysisHandle));
    }

    @Override
    public Optional<TransactionResultAnalysis> characteristic() {
        return Optional.of(this);
    }
}
