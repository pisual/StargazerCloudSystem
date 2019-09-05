package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<ParameterKey, ParameterValue, ResultRecordAnalysisHandle> {
	public void method(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<ResultRecordAnalysisHandle> eventResultRecordAnalysisHandleArg);
	public void fallBack(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<ResultRecordAnalysisHandle> eventResultRecordAnalysisHandleArg, Throwable throwable);
	public Event eventAnnotation();
}
