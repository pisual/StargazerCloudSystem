package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.EventTimeOut;

public interface EventExecuteAnalysisHandle {
    public void run();

    public Optional<EventTimeOut> EventConfiguration();
}
