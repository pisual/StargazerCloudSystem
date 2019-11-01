package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.Event;

public interface TransactionAssembleAnalysisHandle {

    public TransactionAssembleAnalysisHandle injectTransactionParameter(Optional<String> Key, Optional<String> value);

    public void assembleFromJson (Optional<String> jsonData);

    public void addEvent(Optional<Event> event);

}
