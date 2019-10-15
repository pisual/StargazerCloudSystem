package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<ParameterKey, ParameterValue> {
	public void method(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle);
	public void fallBack(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle, Throwable throwable);
	public Event eventAnnotation();
}
