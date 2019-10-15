package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionResultsResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionResultsResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsResultAnalysisImpl")
@Qualifier("transactionResultsResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultsResultAnalysisImpl extends BaseTransactionResultsResultAnalysisImpl implements BeforehandCharacteristicShell<TransactionResultsResultAnalysis> {

	@Qualifier("transactionResultsResultAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionResultsResultAnalysis> transactionResultsResultAnalysisArg) {
		transactionResultsResultAnalysis = transactionResultsResultAnalysisArg.characteristic().get();
	}
}
