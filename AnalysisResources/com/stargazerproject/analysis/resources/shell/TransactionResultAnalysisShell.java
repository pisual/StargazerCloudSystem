package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionResultAnalysisHandleResources;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.transaction.EventResult;

import java.util.Collection;

public class TransactionResultAnalysisShell implements TransactionResultAnalysis {

    @Override
    public Optional<TransactionResultAnalysisHandle> analysis(Optional<Cache<String, String>> resultCache, Optional<Collection<EventResult>> interactionCache) {
        return Optional.of(new TransactionResultAnalysisHandleResources(resultCache, interactionCache));
    }
}
