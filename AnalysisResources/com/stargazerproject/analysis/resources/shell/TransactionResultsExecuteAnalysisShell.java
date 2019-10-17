package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultsExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultsExecuteAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsExecuteAnalysisShell")
@Qualifier("transactionResultsExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionResultsExecuteAnalysisShell implements TransactionResultsExecuteAnalysis, BaseCharacteristic<TransactionResultsExecuteAnalysis> {


    @Override
    public Optional<TransactionResultsExecuteAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache) {
        return Optional.of(new TransactionResultsExecuteAnalysisHandleResources(resultCache));
    }

    @Override
    public Optional<TransactionResultsExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }
}
