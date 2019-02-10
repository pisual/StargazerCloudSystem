package com.stargazerproject.transaction.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.BeforehandCharacteristicShell;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.base.impl.BaseTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="standardTransaction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("standardTransaction")
public class StandardTransaction extends BaseTransaction implements StanderCharacteristicShell<Transaction>, BeforehandCharacteristicShell<Transaction> {

	private static final long serialVersionUID = 422476986033443667L;

	@Override
	public void initialize(Optional<Transaction> transactionArg) {
		transaction = transactionArg.get();
	}

	@Override
	@Qualifier("baseTransactionShell")
	@Autowired
	public void initialize(BaseCharacteristic<Transaction> transactionArg) {
		transaction = transactionArg.characteristic().get();
	}
}
