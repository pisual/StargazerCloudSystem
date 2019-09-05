package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

public interface TransactionResultRecordAnalysisHandle{

    public void errorMessage(Optional<Exception> exception);

}
