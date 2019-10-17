package com.stargazerproject.analysis.configuration;

import com.stargazerproject.analysis.di.configuration.AnalysisDIConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AnalysisDIConfiguration.class})
public class GroupAnalysisConfiguration {

}
