package com.stargazerproject.bus.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.Transaction;
import com.stargazerproject.transaction.date.EventDate;
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

    private static Bus<Transaction> transactionBusImpl;

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
    public void test_2_PushEvent(){
        try {
            transactionBusImpl.push(Optional.of(getNewTransaction()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_PushEvents(){
        try {
            for(int i=0; i<1000; i++){
                transactionBusImpl.push(Optional.of(getNewTransaction()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));
            }

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_pushAsyncEvent(){
        transactionBusImpl.pushAsync(Optional.of(getNewTransaction()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
    }


    @Test
    public void test_4_stopBus(){
        transactionBusImpl.stopBus();
    }


    private Event getNewEvent(){

        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_NowTimeModel"));
        return event;
    }

    private Transaction getNewTransaction(){
        Transaction transaction = super.initializationElement("standardTransaction", Transaction.class);
        TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = transaction.transactionAssemble(Optional.of(transactionAssembleAnalysis)).get();
        transactionAssembleAnalysisHandle.addEvent(Optional.of(getNewEvent()));
        return transaction;
    }
}
