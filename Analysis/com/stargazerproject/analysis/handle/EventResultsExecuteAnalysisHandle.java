package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResultState;

public interface EventResultsExecuteAnalysisHandle {

    public void EventResultState(Optional<EventResultState> eventResultState);

    public void errorMessage(Optional<Throwable> throwable);

    public void resultMessage(Optional<String> key, Optional<String> message);
}
