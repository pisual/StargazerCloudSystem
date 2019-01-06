package com.stargazerproject.analysis.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.LogAnalysis;
import com.stargazerproject.analysis.handle.LogAnalysisHandle;
import com.stargazerproject.log.model.LogData;

public abstract class BaseLogAnalysisImpl implements LogAnalysis{

	protected LogAnalysis logAnalysis;
	
	@Override
	public Optional<LogAnalysisHandle> analysis(Optional<LogData> logData) {
		return logAnalysis.analysis(logData);
	}

}
