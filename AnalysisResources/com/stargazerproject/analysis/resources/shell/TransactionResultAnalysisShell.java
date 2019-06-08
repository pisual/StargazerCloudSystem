package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value="transactionResultAnalysisShell")
@Qualifier("transactionResultAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultAnalysisShell implements TransactionResultAnalysis {

    @Override
    public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Collection<Event>> interactionCache, Optional<Cache<String, String>> resultInteractionCache) {
        return Optional.of(new TransactionResultAnalysisHandleResources(resultCache, interactionCache, resultInteractionCache));
    }

}
