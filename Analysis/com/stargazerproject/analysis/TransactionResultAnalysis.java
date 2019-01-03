package com.stargazerproject.analysis;

import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.transaction.EventResult;

import java.util.List;

public interface TransactionResultAnalysis extends ResultResultAnalysis<List<EventResult>, TransactionResultAnalysisHandle> {
	
}
