package com.stargazerproject.sequence.base.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.analysis.SequenceTransactionResultAnalysis;
import com.stargazerproject.sequence.SequenceObserver;

public class SequenceObserverImpl<K> implements SequenceObserver<K>{
	
	private SequenceTransactionResultAnalysis resultAnalysis;

	private Multimap<String, K> cache;
	
	public SequenceObserverImpl(Optional<SequenceTransactionResultAnalysis> sequenceTransactionResultAnalysisArg, Optional<Multimap<String, K>> eventList) {
		resultAnalysis = sequenceTransactionResultAnalysisArg.get();
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional failResultList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional sequenceResultList() {
		// TODO Auto-generated method stub
		return null;
	}

}
