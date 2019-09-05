package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultRecordAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultRecordAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultRecordAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultRecordAnalysisShell")
@Qualifier("transactionResultRecordAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultRecordAnalysisShell implements TransactionResultRecordAnalysis {

    @Override
    public Optional<TransactionResultRecordAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new TransactionResultRecordAnalysisHandleResources(resultCache));
    }
}
