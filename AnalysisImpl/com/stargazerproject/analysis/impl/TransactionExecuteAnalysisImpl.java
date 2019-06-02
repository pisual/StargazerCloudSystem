package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionExecuteAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionExecuteAnalysisImpl")
@Qualifier("transactionExecuteAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionExecuteAnalysisImpl extends BaseTransactionExecuteAnalysisImpl implements BeforehandCharacteristicShell<TransactionExecuteAnalysis> {

	@Qualifier("transactionExecuteAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionExecuteAnalysis> transactionExecuteAnalysisArg) {
		transactionExecuteAnalysis = transactionExecuteAnalysisArg.characteristic().get();
	}
	
}
