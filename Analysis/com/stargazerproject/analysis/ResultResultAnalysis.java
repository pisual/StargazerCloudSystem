package com.stargazerproject.analysis;

import com.google.common.base.Optional;

public interface ResultResultAnalysis<R, H> {

	/** @illustrate 结果分析器 **/
	public Optional<H> analysis(Optional<R> resultCache);
	
}
