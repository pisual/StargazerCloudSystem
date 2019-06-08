package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<ParameterKey, ParameterValue, ResultKey, ResultValue> {
	public void method(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<Cache<ResultKey, ResultValue>> resultCache);
	public void fallBack(Optional<Cache<ParameterKey, ParameterValue>> cache, Optional<Cache<ResultKey, ResultValue>> resultCache, Throwable throwable);
	public Event eventAnnotation();
}
