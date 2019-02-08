package com.stargazerproject.analysis;

import com.google.common.base.Optional;

public interface ResultResultAnalysis<ResultCache, InteractionCache,  Handle> {

	/** @illustrate 结果分析器 **/
	public Optional<Handle> analysis(Optional<ResultCache> resultCache, Optional<InteractionCache> interactionCache);
	
}
