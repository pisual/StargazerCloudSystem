package com.staragzerproject.transaction.factory.test;

import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Transaction;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *  @name TransactionTest
 *  @illustrate Transaction冒烟测试，会在实际启动的系统中进行功能性测试
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionTest extends BaseJunitTest {

    private Transaction transaction;

    @Test
    public void getTransaction(){
        transaction = super.initializationElement("standardTransaction", Transaction.class);
        System.out.println(transaction.toString());
    }

}
