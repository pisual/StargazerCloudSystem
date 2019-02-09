package com.stargazerproject.analysis;

import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.MultimapCache;

public interface EventResultAnalysis extends ResultResultAnalysis<MultimapCache<String, String>, Cache<String, String>, EventResultAnalysisHandle>{

}
