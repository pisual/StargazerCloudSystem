package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionResultsAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionResultsAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsAssembleAnalysisImpl")
@Qualifier("transactionResultsAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultsAssembleAnalysisImpl extends BaseTransactionResultsAssembleAnalysisImpl implements BeforehandCharacteristicShell<TransactionResultsAssembleAnalysis> {

	@Qualifier("transactionResultsAssembleAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionResultsAssembleAnalysis> transactionResultsAssembleAnalysisArg) {
		transactionResultsAssembleAnalysis = transactionResultsAssembleAnalysisArg.characteristic().get();
	}
}
