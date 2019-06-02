package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.exception.SequenceTimeOutException;

public class BaseSequence<K> implements Sequence<K>{
	
	protected Sequence<K> sequence;

	@Override
	public Optional<SequenceObserver<K>> startBlockSequence(Optional<K> transaction) throws SequenceTimeOutException {
		return sequence.startBlockSequence(transaction);
	}

	@Override
	public Optional<SequenceObserver<K>> startSequence(Optional<K> transaction) {
		return sequence.startSequence(transaction);
	}

}
