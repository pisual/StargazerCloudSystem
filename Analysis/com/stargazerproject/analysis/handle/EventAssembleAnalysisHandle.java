package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;

public interface EventAssembleAnalysisHandle {

    public void injectEventParameter(Optional<String> Key, Optional<String> value);

    public void injecrParametersFromJson(Optional<String> json);

}
