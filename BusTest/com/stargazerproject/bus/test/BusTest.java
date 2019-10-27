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

    private static Bus<Event, BusEventTimeoutException> eventBusImpl;

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
    public void test_2_1_0_PushEvent() throws BusEventTimeoutException {
        eventBusImpl.push(Optional.of(getNewEvent()));
    }

    /**本测试方法具有破坏性，会破坏其他的测试方法，首先需要把处理线程塞满阻塞住的情况下才能测试等待超时，所以请单独测试此方法**/
    @Test
    public void test_9_9_1_PushEventWaitTimeOut() throws BusEventTimeoutException {

        for(int i=0; i<10; i++) {
        new Thread(() -> {
            try {
                eventBusImpl.push(Optional.of(getTimeOutMethodEvent_WhileWait()));
            } catch (BusEventTimeoutException e) {
                e.printStackTrace();
            }
        }){}.start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        expection.expect(BusEventTimeoutException.class);
        expection.expectMessage("Event没有在指定时间内开始任务 : BaseEvent Not Start at the specified time : " + EventResultState.WAIT);
        BusObserver busObserver =  eventBusImpl.push(Optional.of(getTimeOutMethodEvent_WhileWait_WaitTimeOut())).get();
    }

    @Test
    public void test_2_1_2_PushEventRunTimeOut() throws BusEventTimeoutException {
        expection.expect(BusEventTimeoutException.class);
        expection.expectMessage("Event没有在指定时间内完成任务 : BaseEvent Not Complete at the specified time : " + EventResultState.Run);
        BusObserver busObserver =  eventBusImpl.push(Optional.of(getTimeOutMethodEvent_WhileWait_RunTimeOut())).get();
    }


    @Test
    public void test_3_1_pushAsyncEvent() throws BusEventTimeoutException {
        BusObserver<Event, BusEventTimeoutException> busObserver =  eventBusImpl.pushAsync(Optional.of(getNewEvent())).get();
        busObserver.waitFinish();
    }

    /**本测试方法具有破坏性，会破坏其他的测试方法，首先需要把处理线程塞满阻塞住的情况下才能测试等待超时，所以请单独测试此方法**/
    @Test
    public void test_9_9_0_pushAsyncEventWaitTimeOut() throws BusEventTimeoutException {

        for(int i=0; i<10; i++) {
            BusObserver busObserver =  eventBusImpl.pushAsync(Optional.of(getTimeOutMethodEvent_WhileWait())).get();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        expection.expect(BusEventTimeoutException.class);
        expection.expectMessage("Event没有在指定时间内开始任务 : BaseEvent Not Start at the specified time : " + EventResultState.WAIT);
        BusObserver<Event, BusEventTimeoutException> busObserver =  eventBusImpl.pushAsync(Optional.of(getTimeOutMethodEvent_WhileWait_WaitTimeOut())).get();
        busObserver.waitFinish();
    }


    @Test
    public void test_3_3_PushAsyncEventRunTimeOut() throws BusEventTimeoutException {
        expection.expect(BusEventTimeoutException.class);
        expection.expectMessage("Event没有在指定时间内完成任务 : BaseEvent Not Complete at the specified time : " + EventResultState.Run);
        BusObserver<Event, BusEventTimeoutException> busObserver =  eventBusImpl.pushAsync(Optional.of(getTimeOutMethodEvent_WhileWait_RunTimeOut())).get();
        busObserver.waitFinish();
    }

    @Test
    public void test_3_4_PushAsyncEventRunHystrixTimeOut() throws BusEventTimeoutException {
        Event event = getHystrixTimeOutMethodEvent();

        BusObserver<Event, BusEventTimeoutException> busObserver =  eventBusImpl.pushAsync(Optional.of(event)).get();
        busObserver.waitFinish();

        EventResultAnalysisHandle eventResultAnalysisHandle = event.eventResult(Optional.of(eventResultAnalysis)).get();

        assertEquals(eventResultAnalysisHandle.getTheLastEventResultState().get(), EventResultState.FAULT);
        assertEquals(eventResultAnalysisHandle.getTheLastErrorMessage().get(), "com.netflix.hystrix.exception.HystrixTimeoutException");
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
