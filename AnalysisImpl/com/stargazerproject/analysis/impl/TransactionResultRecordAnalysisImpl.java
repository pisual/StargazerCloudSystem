package com.stargazerproject.analysis.impl;

import com.stargazerproject.analysis.EventResultRecordAnalysis;
import com.stargazerproject.analysis.base.impl.BaseEventResultRecordAnalysisImpl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionResultRecordAnalysisImpl")
@Qualifier("transactionResultRecordAnalysisImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionResultRecordAnalysisImpl extends BaseEventResultRecordAnalysisImpl implements BeforehandCharacteristicShell<EventResultRecordAnalysis> {

	@Qualifier("transactionResultRecordAnalysisShell")
	@Autowired
	@Override
	public void initialize(BaseCharacteristic<EventResultRecordAnalysis> eventResultRecordAnalysisArg) {
		eventResultRecordAnalysis = eventResultRecordAnalysisArg.characteristic().get();
	}
}
