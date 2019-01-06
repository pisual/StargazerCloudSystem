package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.LogAnalysisHandle;
import com.stargazerproject.log.model.LogData;

public interface LogAnalysis {
	public Optional<LogAnalysisHandle> analysis(Optional<LogData> logData);
}
