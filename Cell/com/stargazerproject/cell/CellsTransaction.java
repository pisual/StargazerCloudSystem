package com.stargazerproject.cell;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

public interface CellsTransaction<E, A> {
	public Optional<Cache<E, A>> method(Optional<Cache<E, A>> cache);
	public Optional<Cache<E, A>> fallBack(Optional<Cache<E, A>> cache, Throwable throwable);
}
