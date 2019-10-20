package com.staragzerproject.transaction.factory.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionAssembleAnalysis;
import com.stargazerproject.analysis.handle.TransactionAssembleAnalysisHandle;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 *  @name TransactionTest
 *  @illustrate Transaction冒烟测试，会在实际启动的系统中进行功能性测试
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionTest extends BaseJunitTest {

    private static Transaction transaction;

    private static TransactionAssembleAnalysis transactionAssembleAnalysis;

    private static Event event;

    @Rule
    public ExpectedException expection = ExpectedException.none();

    @Test
    public void test_0_initiation(){
        transactionAssembleAnalysis = initializationElement("transactionAssembleAnalysisImpl", TransactionAssembleAnalysis.class);
        event = super.initializationElement("standardEvent", Event.class);
    }

    @Test
    public void test_1_getInitiationTransaction(){
        transaction = super.initializationElement("standardTransaction", Transaction.class);
        System.out.println(transaction.toString());
    }

    @Test
    public void test_2_injectEvents(){
        TransactionAssembleAnalysisHandle transactionAssembleAnalysisHandle = transaction.transactionAssemble(Optional.of(transactionAssembleAnalysis)).get();
        transactionAssembleAnalysisHandle.addEvent(Optional.of(event));
        System.out.println(transaction.toString());
    }

}
