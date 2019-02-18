package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<E, A> {
	public void method(Optional<Cache<E, A>> cache);
	public void fallBack(Optional<Cache<E, A>> cache, Throwable throwable);
}
