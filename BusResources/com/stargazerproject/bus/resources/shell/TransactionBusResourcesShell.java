package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component(value="transactionBusResourcesShell")
@Qualifier("transactionBusResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusResourcesShell implements Bus<Transaction>, BaseCharacteristic<Bus<Transaction>>{

	@Autowired
	@Qualifier("transactionBusBlockMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusBlockMethod<Transaction>> transactionBusBlockMethodMBassadorCharacteristic;

	@Autowired
	@Qualifier("transactionBusAsyncMethodMBassadorCharacteristic")
	private BaseCharacteristic<BusAsyncMethod<Transaction>> transactionBusAsyncMethodMBassadorCharacteristic;

	private BusBlockMethod<Transaction> busBlockMethod;

	private BusAsyncMethod<Transaction> busAsyncMethod;

	public TransactionBusResourcesShell() {}

	@Override
	public Optional<Bus<Transaction>> characteristic() {
		busBlockMethod = transactionBusBlockMethodMBassadorCharacteristic.characteristic().get();
		busAsyncMethod = transactionBusAsyncMethodMBassadorCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<BusObserver<Transaction>> push(Optional<Transaction> event, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		return busBlockMethod.push(event, timeUnit, timeout);
	}

	@Override
	public Optional<BusObserver<Transaction>> pushAsync(Optional<Transaction> event, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		return busAsyncMethod.pushAsync(event, timeUnit, timeout);
	}

	@Override
	public void startBus() {

	}

	@Override
	public void stopBus() {

	}

}