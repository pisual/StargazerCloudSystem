package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionBusResourcesShell")
@Qualifier("transactionBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusResourcesShell implements Bus<Transaction, BusTransactionTimeoutException>, BaseCharacteristic<Bus<Transaction, BusTransactionTimeoutException>>{

	@Autowired
	@Qualifier("transactionBusBlockMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusBlockMethod<Transaction, BusTransactionTimeoutException>> transactionBusBlockMethodMBassadorCharacteristic;

	@Autowired
	@Qualifier("transactionBusAsyncMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusAsyncMethod<Transaction, BusTransactionTimeoutException>> transactionBusAsyncMethodMBassadorCharacteristic;

	private BusBlockMethod<Transaction, BusTransactionTimeoutException> busBlockMethod;

	private BusAsyncMethod<Transaction, BusTransactionTimeoutException> busAsyncMethod;

	public TransactionBusResourcesShell() {}

	@Override
	public Optional<Bus<Transaction, BusTransactionTimeoutException>> characteristic() {
		busBlockMethod = transactionBusBlockMethodMBassadorCharacteristic.characteristic().get();
		busAsyncMethod = transactionBusAsyncMethodMBassadorCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<BusObserver<Transaction, BusTransactionTimeoutException>> push(Optional<Transaction> transaction) throws BusTransactionTimeoutException {
		return busBlockMethod.push(transaction);
	}

	@Override
	public Optional<BusObserver<Transaction, BusTransactionTimeoutException>> pushAsync(Optional<Transaction> transaction) {
		return busAsyncMethod.pushAsync(transaction);
	}

	@Override
	public void startBus() {

	}

	@Override
	public void stopBus() {

	}

}