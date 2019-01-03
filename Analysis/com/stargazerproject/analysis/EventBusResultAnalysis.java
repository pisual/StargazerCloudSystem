package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.EventBusResultAnalysisHandle;
import com.stargazerproject.bus.BusEventTimeoutModel;
import com.stargazerproject.transaction.EventResult;

import java.util.List;

public interface EventBusResultAnalysis extends ResultResultAnalysis<List<EventResult>, EventBusResultAnalysisHandle>{

	/** @illustrate 获取Event超时控制参数 **/
	public Optional<BusEventTimeoutModel> busEventTimeout();
	
}
