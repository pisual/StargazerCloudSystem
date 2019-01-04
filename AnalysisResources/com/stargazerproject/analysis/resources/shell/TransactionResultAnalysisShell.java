package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultAnalysisHandleResources;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public class TransactionResultAnalysisShell implements TransactionResultAnalysis {

    @Override
    public Optional<TransactionResultAnalysisHandle> analysis(Optional<Collection<EventResult>> resultCache) {
        return Optional.of(new TransactionResultAnalysisHandleResources(resultCache));
    }
}
