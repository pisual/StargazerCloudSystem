package com.stargazerproject.bus.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultAnalysisHandle;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.TransactionResultState;

import java.util.concurrent.TimeUnit;

public class TransactionBusObserver implements BusObserver{

	private TransactionResultAnalysisHandle transactionResultAnalysisHandle;

	private TransactionExecuteAnalysisHandle transactionExecuteAnalysisHandle;

	public TransactionBusObserver(Optional<TransactionExecuteAnalysisHandle> transactionExecuteAnalysisHandleArg,
								  Optional<TransactionResultAnalysisHandle>  transactionResultAnalysisHandleArg){
		transactionResultAnalysisHandle =  transactionResultAnalysisHandleArg.get();
		transactionExecuteAnalysisHandle = transactionExecuteAnalysisHandleArg.get();
	}

	@Override
	public Optional<Boolean> isComplete(){
		if(transactionResultAnalysisHandle.getTheLastTransactionResultState().get() == TransactionResultState.SUCCESS ||
			transactionResultAnalysisHandle.getTheLastTransactionResultState().get() == TransactionResultState.FAULT){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.TRUE);
		}
	}

	@Override
	public Optional<Boolean> isRunning(){
		if(transactionResultAnalysisHandle.getTheLastTransactionResultState().get() == TransactionResultState.Run){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.TRUE);
		}
	}

	@Override
	public Optional<Boolean> hasError(){
		transactionResultAnalysisHandle.getTheLastErrorMessage();
		return Optional.of(transactionResultAnalysisHandle.getTheLastErrorMessage().isPresent());
	}

	@Override
	public Optional<String> getError(){
		return transactionResultAnalysisHandle.getTheLastErrorMessage();
	}

	@Override
	public Optional<Boolean> testFinish(){
		if(isComplete().get() == Boolean.TRUE){
			return Optional.of(Boolean.TRUE);
		}
		else{
			return Optional.of(Boolean.FALSE);
		}
	}

	@Override
	public Optional<BusObserver<Transaction, BusTransactionTimeoutException>> waitFinish() throws BusTransactionTimeoutException {
		waitStart();
		waitComplete(transactionExecuteAnalysisHandle.runTimeoutUnit().get(), transactionExecuteAnalysisHandle.runTimeout().get());
		return Optional.of(this);
	}

	private void waitComplete(TimeUnit runTimeUnit, Integer runTimeout) throws BusTransactionTimeoutException{
		for(int i=0; i<runTimeout; i++){
			if(transactionResultAnalysisHandle.getTheLastTransactionResultState().get() == TransactionResultState.SUCCESS ||
			   transactionResultAnalysisHandle.getTheLastTransactionResultState().get() == TransactionResultState.FAULT){
				return;
			}
			else{
				sleep(runTimeUnit);
				continue;
			}
		}
		throw new BusTransactionTimeoutException("Transaction没有在指定时间内完成任务 : BaseTransaction Not Complete at the specified time : " + transactionResultAnalysisHandle.getTheLastTransactionResultState().get());
	}

	private void waitStart() throws BusTransactionTimeoutException{
		Integer waitTimeout = transactionExecuteAnalysisHandle.waitTimeout().get();
		TimeUnit  waitTimeoutUni = transactionExecuteAnalysisHandle.waitTimeoutUnit().get();
		for(int i=0; i<waitTimeout; i++){
			if(transactionResultAnalysisHandle.getTheLastTransactionResultState().get() != TransactionResultState.WAIT) {
				return;
			}
			else{
				sleep(waitTimeoutUni);
				continue;
			}
		}
		throw new BusTransactionTimeoutException("Transaction没有在指定时间内开始任务 : BaseTransaction Not Start at the specified time : " + transactionResultAnalysisHandle.getTheLastTransactionResultState().get());
	}

	private void sleep(TimeUnit timeUnit){
		try {
			switch (timeUnit) {
				case SECONDS:
					TimeUnit.SECONDS.sleep(1);
					break;
				case MICROSECONDS:
					TimeUnit.MICROSECONDS.sleep(1);
					break;
				case MILLISECONDS:
					TimeUnit.MILLISECONDS.sleep(1);
					break;
				case NANOSECONDS:
					TimeUnit.NANOSECONDS.sleep(1);
					break;
				default:
					TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
