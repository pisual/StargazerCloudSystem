package com.stargazerproject.serializable.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.serializable.Serializables;
import com.stargazerproject.serializable.base.impl.BaseSerializablesImpl;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionTransmissionSerializables")
@Qualifier("transactionTransmissionSerializables")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionTransmissionSerializables extends BaseSerializablesImpl implements StanderCharacteristicShell<Serializables<Transaction, byte[]>>{

	public TransactionTransmissionSerializables() {}
	
	@Override
	public void initialize(Optional<Serializables<Transaction, byte[]>> serializablesArg) {
		serializables = serializablesArg.get();
	}

//	@Override
//	@Qualifier("transactionTransmissionSerializablesShell")
//	@Autowired
//	public void initialize(BaseCharacteristic<Serializables<Transaction, byte[]>> serializablesArg) {
//		serializables = serializablesArg.characteristic().get();
//	}
	
}
