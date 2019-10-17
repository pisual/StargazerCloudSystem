package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsAssembleAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultsAssembleAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsAssembleAnalysisShell")
@Qualifier("transactionResultsAssembleAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultsAssembleAnalysisShell implements TransactionResultsAssembleAnalysis, BaseCharacteristic<TransactionResultsAssembleAnalysis> {


    @Override
    public Optional<TransactionResultsAssembleAnalysisHandle> analysis(Optional<Cache<String, String>> resultCacheArg) {
        return Optional.of(new TransactionResultsAssembleAnalysisHandleResources(resultCacheArg));
    }

    @Override
    public Optional<TransactionResultsAssembleAnalysis> characteristic() {
        return Optional.of(this);
    }
}
