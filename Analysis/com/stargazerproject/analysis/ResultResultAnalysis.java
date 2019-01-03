package com.stargazerproject.analysis;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.ResultState;

public interface ResultResultAnalysis<R, H> {

	/** @illustrate 获取结果状态 
	 * **/
	public Optional<ResultState> resultState();
	
	/** @illustrate 结果分析器 **/
	public Optional<H> analysis(Optional<R> resultCache);
	
}
