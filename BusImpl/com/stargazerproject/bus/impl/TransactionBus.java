package com.stargazerproject.bus.impl;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.base.impl.BusImpl;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionBus")
@Qualifier("transactionBus")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBus extends BusImpl<Transaction, BusTransactionTimeoutException> implements StanderCharacteristicShell<Bus<Transaction, BusTransactionTimeoutException>>{

	public TransactionBus() {
		super();
		}

	@Override
	public void initialize(Optional<Bus<Transaction, BusTransactionTimeoutException>> busArg) {
		bus = busArg.get();
	}

}
