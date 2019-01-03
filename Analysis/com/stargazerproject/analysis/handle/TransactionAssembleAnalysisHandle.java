package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.Event;

public interface TransactionAssembleAnalysisHandle {

    public void addEvent(Optional<Event> event);

}
