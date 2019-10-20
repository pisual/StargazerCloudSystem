package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.transaction.Transaction;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="transactionBusListener")
@Qualifier("transactionBusListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Listener(references = References.Strong)
public class TransactionBusListener implements BusListener<Optional<Transaction>>{

    @Autowired
    @Qualifier("transactionExecuteAnalysisImpl")
    private TransactionExecuteAnalysis transactionExecuteAnalysis;

    @Handler(delivery = Invoke.Asynchronously)
    @Override
    public void handler(Optional<Transaction> busTransaction) {
        try {
            TransactionExecuteAnalysisHandle transactionExecuteAnalysisHandle = busTransaction.get().transactionExecute(Optional.of(transactionExecuteAnalysis)).get();
            transactionExecuteAnalysisHandle.startTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
