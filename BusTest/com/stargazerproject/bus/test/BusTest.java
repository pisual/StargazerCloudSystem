package com.stargazerproject.bus.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventResultState;
import com.stargazerproject.transaction.date.EventDate;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

/**
 *  @name BusTest
 *  @illustrate Bus冒烟测试，会在实际启动的系统中进行功能性测试
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusTest extends BaseJunitTest {

    private static Bus<Event> eventBusImpl;

    private static EventAssembleAnalysis eventAssembleAnalysis;

    private static EventResultAnalysis eventResultAnalysis;


    @Rule
    public ExpectedException expection = ExpectedException.none();

    @Test
    public void test_0_initiation(){
        eventBusImpl = initializationElement("eventBusImpl", Bus.class);
        eventAssembleAnalysis = initializationElement("eventAssembleAnalysisImpl", EventAssembleAnalysis.class);
        eventResultAnalysis = initializationElement("eventResultAnalysisImpl", EventResultAnalysis.class);
    }

    @Test
    public void test_1_startBus(){
        eventBusImpl.startBus();
    }

    @Test
    public void test_2_1_PushEvent(){
        try {
            eventBusImpl.push(Optional.of(getNewEvent()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_2_PushEvents(){
        try {
            for(int i=0; i<5; i++){
                eventBusImpl.push(Optional.of(getNewEvent()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));
            }

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_3_pushAsyncEvent(){
        eventBusImpl.pushAsync(Optional.of(getNewEvent()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
    }

    @Test
    public void test_3_4_pushAsyncEvents(){
        for(int i=0; i<10; i++){
            eventBusImpl.pushAsync(Optional.of(getNewEvent()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
        }
    }

    @Test
    public void test_3_5_testEventsAsyncRunTimeout(){
        Event event = getTimeOutMethodEvent();
        BusObserver busObserver = eventBusImpl.pushAsync(Optional.of(event), Optional.of(TimeUnit.SECONDS), Optional.of(100)).get();

        //判断是否执行完毕
        while(busObserver.isComplete().get() != Boolean.TRUE){ }

        EventResultAnalysisHandle eventResultAnalysisHandle = event.eventResult(Optional.of(eventResultAnalysis)).get();

        //判断FallBack是否执行完毕
        while(eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.WAIT){ }

        //自行结果断言
        assertEquals(eventResultAnalysisHandle.getTheLastEventResultState().get(), EventResultState.FAULT);
        assertEquals(eventResultAnalysisHandle.getTheLastErrorMessage().get(), "com.netflix.hystrix.exception.HystrixTimeoutException");

    }

    @Test
    public void test_3_6_testEventsRunTimeout(){
        Event event = getTimeOutMethodEvent();
        BusObserver busObserver = null;

        try {
            busObserver = eventBusImpl.push(Optional.of(event), Optional.of(TimeUnit.SECONDS), Optional.of(1000)).get();
        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }

        //判断是否执行完毕
        while(busObserver.isComplete().get() != Boolean.TRUE){ }

        EventResultAnalysisHandle eventResultAnalysisHandle = event.eventResult(Optional.of(eventResultAnalysis)).get();

        //判断FallBack是否执行完毕
        while(eventResultAnalysisHandle.getTheLastEventResultState().get() == EventResultState.WAIT){ }

        //自行结果断言
        assertEquals(eventResultAnalysisHandle.getTheLastEventResultState().get(), EventResultState.FAULT);
        assertEquals(eventResultAnalysisHandle.getTheLastErrorMessage().get(), "com.netflix.hystrix.exception.HystrixTimeoutException");
    }

    @Test
    public void test_3_7_testEventsBlockMethodWaitTimeout() throws BusEventTimeoutException {

        for(int i=0; i<20; i++){

            new Thread(() -> {
                try {
                    eventBusImpl.push(Optional.of(getTimeOutMethodEvent_WhileWait()), Optional.of(TimeUnit.SECONDS), Optional.of(1000));
                } catch (BusEventTimeoutException e) {
                    e.printStackTrace();
                }
            }){}.start();
        }
            expection.expect(BusEventTimeoutException.class);
            eventBusImpl.push(Optional.of(getTimeOutMethodEvent_WhileWait()), Optional.of(TimeUnit.SECONDS), Optional.of(1));

    }



    @Test
    public void test_4_stopBus(){
        eventBusImpl.stopBus();
    }


    private Event getNewEvent(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_NowTimeModel"));
        return event;
    }

    private Event getTimeOutMethodEvent_WhileWait(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("1"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }

    private Event getTimeOutMethodEvent(){
        Event event = super.initializationElement("standardEvent", Event.class);
        /**Event注入需要测试的方法**/
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.Method.toString()), Optional.of("test_TimeOutMethod_WhileWait_HystrixTimeOut"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeout.toString()), Optional.of("1"));
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of(EventDate.RunTimeoutUnit.toString()), Optional.of(TimeUnit.SECONDS.toString()));
        return event;
    }
}
