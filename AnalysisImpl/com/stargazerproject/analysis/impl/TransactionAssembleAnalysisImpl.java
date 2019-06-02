package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.base.impl.BaseTransactionAssembleAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionAssembleAnalysisImpl")
@Qualifier("transactionAssembleAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionAssembleAnalysisImpl extends BaseTransactionAssembleAnalysisImpl implements BeforehandCharacteristicShell<TransactionAssembleAnalysis> {

	@Qualifier("transactionAssembleAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<TransactionAssembleAnalysis> transactionAssembleAnalysisArg) {
		transactionAssembleAnalysis = transactionAssembleAnalysisArg.characteristic().get();
	}

}
