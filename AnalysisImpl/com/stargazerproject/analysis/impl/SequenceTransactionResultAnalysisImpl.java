package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.base.impl.BaseSequenceTransactionResultAnalysisImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="sequenceTransactionResultAnalysisImpl")
@Qualifier("sequenceTransactionResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SequenceTransactionResultAnalysisImpl extends BaseSequenceTransactionResultAnalysisImpl {
}
