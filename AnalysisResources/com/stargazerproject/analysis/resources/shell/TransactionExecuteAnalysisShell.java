package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.handle.TransactionExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionResultsExecuteAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.TransactionExecuteAnalysisHandleResoources;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.job.JobObserver;
import com.stargazerproject.job.JobRun;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;

@Component(value="transactionExecuteAnalysisShell")
@Qualifier("transactionExecuteAnalysisShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionExecuteAnalysisShell implements TransactionExecuteAnalysis, BaseCharacteristic<TransactionExecuteAnalysis> {

    @Autowired
    @Qualifier("eventExecuteAnalysisImpl")
    private EventExecuteAnalysis eventExecuteAnalysis;

    /** @illustrate EventBus接口 **/
    @Autowired
    @Qualifier("eventBusImpl")
    private Bus<Event, BusEventTimeoutException> eventBus;

    @Autowired
    @Qualifier("cycleJob")
    private JobRun<ScheduledExecutorService, JobObserver> jobRun;

    @Override
    public Optional<TransactionExecuteAnalysisHandle> analysis(Optional<Collection<Event>> eventList,
                                                               Optional<Cache<String, String>> transactionInteractionCache,
                                                               Optional<TransactionState> transactionState,
                                                               Optional<TransactionResultsExecuteAnalysisHandle> transactionResultsExecuteAnalysisHandleArg) {
        return Optional.of(new TransactionExecuteAnalysisHandleResoources(eventList, transactionInteractionCache, transactionState, transactionResultsExecuteAnalysisHandleArg, Optional.of(eventBus), Optional.of(jobRun)));
    }

    @Override
    public Optional<TransactionExecuteAnalysis> characteristic() {
        return Optional.of(this);
    }

}
