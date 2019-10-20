package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

public interface EventResultsAssembleAnalysisHandle {

    public EventResultsAssembleAnalysisHandle injectEventResultsParameter(Optional<String> key, Optional<String> value);
}
