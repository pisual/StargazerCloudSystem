package com.stargazerproject.bus.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.date.EventDate;
import com.stargazerproject.transaction.date.TransactionDate;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

/**
 *  @name TransactionTest
 *  @illustrate Bus冒烟测试，会在实际启动的系统中进行功能性测试
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionTest extends BaseJunitTest {

    private static Bus<Transaction, BusTransactionTimeoutException> transactionBusImpl;

    private static TransactionAssembleAnalysis transactionAssembleAnalysis;

    private static EventAssembleAnalysis eventAssembleAnalysis;

    @Rule
    public ExpectedException expection = ExpectedException.none();



    @Test
    public void test_0_initiation(){
        transactionBusImpl = initializationElement("transactionBus", Bus.class);
        eventAssembleAnalysis = initializationElement("eventAssembleAnalysisImpl", EventAssembleAnalysis.class);
        transactionAssembleAnalysis = initializationElement("transactionAssembleAnalysisImpl", TransactionAssembleAnalysis.class);

    }

    @Test
    public void test_1_startBus(){
        transactionBusImpl.startBus();
    }

    @Test
    public void test_2_PushTransaction() throws BusTransactionTimeoutException {
        transactionBusImpl.push(Optional.of(getNewTransaction_NeverTimeOut()));
        while(true){}
    }

//    @Test
//    public void test_2_PushAsyncTransaction(){
//        BusObserver<Transaction, BusTransactionTimeoutException> busObserver = transactionBusImpl.pushAsync(Optional.of(getNewTransaction())).get();
//        busObserver.
//    }
//
//    @Test
//    public void test_3_PushEvents(){
//        try {
//            for(int i=0; i<1000; i++){
//                transactionBusImpl.push(Optional.of(getNewTransaction()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));
//            }
//
//        } catch (BusEventTimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test_3_pushAsyncEvent(){
//        transactionBusImpl.pushAsync(Optional.of(getNewTransaction()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
//    }
//
//
//    @Test
//    public void test_4_stopBus(){
//        transactionBusImpl.stopBus();
//    }
//
//

    private Transaction getNewTransaction_NeverTimeOut(){
        Transaction transaction = super.initializationElement("standardTransaction", Transaction.class);
        TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = transaction.transactionAssemble(Optional.of(transactionAssembleAnalysis)).get();
        transactionAssembleAnalysisHandle.addEvent(Optional.of(getNewEvent_wait5Second()));
        transactionAssembleAnalysisHandle.injectTransactionParameter(Optional.of(TransactionDate.WaitTimeout.toString()), Optional.of("100"));
        transactionAssembleAnalysisHandle.injectTransactionParameter(Optional.of(TransactionDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        transactionAssembleAnalysisHandle.injectTransactionParameter(Optional.of(TransactionDate.RunTimeout.toString()), Optional.of("100"));
        transactionAssembleAnalysisHandle.injectTransactionParameter(Optional.of(TransactionDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return transaction;
    }

    private Event getNewEvent_wait5Second(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_NowTimeModel"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }

    private Event getNewEvent(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_NowTimeModel"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("5"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("10"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }

    private Event getTimeOutMethodEvent_WhileWait(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }

    private Event getTimeOutMethodEvent_WhileWait_WaitTimeOut(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("5"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.MICROSECONDS.toString()));
        return event;
    }

    private Event getTimeOutMethodEvent_WhileWait_RunTimeOut(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("5"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("10"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }

    private Event getHystrixTimeOutMethodEvent(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait_HystrixTimeOut"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("100"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeout.toString()), Optional.of("10"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.WaitTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }
}
