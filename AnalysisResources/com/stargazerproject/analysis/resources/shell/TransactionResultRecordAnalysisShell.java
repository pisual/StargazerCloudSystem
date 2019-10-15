package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultsAssembleAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultRecordAnalysisShell")
@Qualifier("transactionResultRecordAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultRecordAnalysisShell implements TransactionResultsAssembleAnalysis {

    @Override
    public Optional<TransactionResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new TransactionResultsAssembleAnalysisHandleResources(resultCache));
    }
}
