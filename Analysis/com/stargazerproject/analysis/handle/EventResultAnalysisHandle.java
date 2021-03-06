package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResultState;

public interface EventResultAnalysisHandle {

    public Optional<EventResultState> getTheLastEventResultState();
    public Optional<String> getTheLastErrorMessage();
}
