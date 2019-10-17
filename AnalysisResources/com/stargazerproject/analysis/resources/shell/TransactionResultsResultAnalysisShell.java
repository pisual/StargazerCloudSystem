package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultsResultAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsResultAnalysisShell")
@Qualifier("transactionResultsResultAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultsResultAnalysisShell implements TransactionResultsResultAnalysis, BaseCharacteristic<TransactionResultsResultAnalysis> {


    @Override
    public Optional<TransactionResultsResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new TransactionResultsResultAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<TransactionResultsResultAnalysis> characteristic() {
        return Optional.of(this);
    }
}
