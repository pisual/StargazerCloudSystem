package com.stargazerproject.sequence.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.sequence.exception.SequenceTimeOutException;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Transaction>, BaseCharacteristic<Sequence<Transaction>>{

	@Autowired
	@Qualifier("sequenceTransactionCharacteristic")
	private BaseCharacteristic<SequenceTransaction<Transaction>> sequenceTransactionCharacteristic;

	private SequenceTransaction<Transaction> sequenceTransaction;

	@Override
	public Optional<Sequence<Transaction>> characteristic() {
		sequenceTransaction = sequenceTransactionCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startBlockSequence(Optional<Transaction> transaction) throws SequenceTimeOutException {
		return sequenceTransaction.startBlockSequence(transaction);
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startSequence(Optional<Transaction> transaction) {
		return sequenceTransaction.startSequence(transaction);
	}
}