package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionResultsExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionResultsExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultsExecuteAnalysisImpl")
@Qualifier("transactionResultsExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultsExecuteAnalysisImpl extends BaseTransactionResultsExecuteAnalysisImpl implements BeforehandCharacteristicShell<TransactionResultsExecuteAnalysis> {

	@Qualifier("transactionResultsExecuteAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionResultsExecuteAnalysis> transactionResultsExecuteAnalysisArg) {
		transactionResultsExecuteAnalysis = transactionResultsExecuteAnalysisArg.characteristic().get();
	}
}
