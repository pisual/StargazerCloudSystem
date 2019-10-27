package com.stargazerproject.sequence.resources;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.sequence.exception.SequenceTimeOutException;
import com.stargazerproject.sequence.impl.SequenceObserverImpl;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="sequenceTransactionCharacteristic")
@Qualifier("sequenceTransactionCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SequenceTransactionCharacteristic implements SequenceTransaction<Transaction>, BaseCharacteristic<SequenceTransaction<Transaction>> {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod logMethod;

	@Autowired
	@Qualifier("transactionBus")
	private BusBlockMethod<Transaction, BusTransactionTimeoutException> transactionBus;

	@Autowired
	@Qualifier("transactionBus")
	private BusAsyncMethod<Transaction, BusTransactionTimeoutException> transactionAsyncBus;

	@Autowired
	@Qualifier("transactionAssembleAnalysisImpl")
	private TransactionAssembleAnalysis transactionAssembleAnalysisImpl;

	public SequenceTransactionCharacteristic(){ }

	@Override
	public Optional<SequenceTransaction<Transaction>> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startBlockSequence(Optional<Transaction> transaction) throws SequenceTimeOutException{
		TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = getTransactionAssembleAnalysisHandle(transaction);

		try {
			Optional<BusObserver<Transaction, BusTransactionTimeoutException>> busObserver = transactionBus.push(transaction);
			return Optional.of(new SequenceObserverImpl(busObserver));
		} catch (BusTransactionTimeoutException e) {
			logMethod.WARN(transaction, e.getMessage());
			throw new SequenceTimeOutException(e.getMessage());
		}
	}

	@Override
	public Optional<SequenceObserver<Transaction>> startSequence(Optional<Transaction> transaction) {
		TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = getTransactionAssembleAnalysisHandle(transaction);
		Optional<BusObserver<Transaction, BusTransactionTimeoutException>> busObserver = transactionAsyncBus.pushAsync(transaction);
		return Optional.of(new SequenceObserverImpl(busObserver));
	}

	private TransactionAssembleAnalysisHandle getTransactionAssembleAnalysisHandle(Optional<Transaction> transaction){
		return transaction.get().transactionAssemble(Optional.of(transactionAssembleAnalysisImpl)).get();
	}

}
