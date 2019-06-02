package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionResultAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultAnalysisImpl")
@Qualifier("transactionResultAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultAnalysisImpl extends BaseTransactionResultAnalysisImpl implements BeforehandCharacteristicShell<TransactionResultAnalysis> {

	@Qualifier("transactionResultAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionResultAnalysis> transactionResultAnalysisArg) {
		transactionResultAnalysis = transactionResultAnalysisArg.characteristic().get();
	}
	
}
