package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.TransactionResultState;

public interface TransactionResultsResultAnalysisHandle {
    public Optional<TransactionResultState> getTheLastTransactionResultState();
    public Optional<String> getTheLastErrorMessage();
}
