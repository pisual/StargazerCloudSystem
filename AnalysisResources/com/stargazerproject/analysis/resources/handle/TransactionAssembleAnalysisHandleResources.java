package com.stargazerproject.analysis.resources.handle;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.transaction.Event;

import java.util.Collection;

public class TransactionAssembleAnalysisHandleResources implements TransactionAssembleAnalysisHandle {

    private Collection<Event> eventList;

    public TransactionAssembleAnalysisHandleResources(Optional<Collection<Event>> eventListArg){
        eventList = eventListArg.get();
    }

    @Override
    public void addEvent(Optional<Event> eventArg) {
        eventList.add(eventArg.get());
    }
}
