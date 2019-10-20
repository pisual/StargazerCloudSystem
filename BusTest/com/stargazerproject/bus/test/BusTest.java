package com.stargazerproject.bus.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.date.EventDate;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

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


    @Rule
    public ExpectedException expection = ExpectedException.none();

    @Test
    public void test_0_initiation(){
        eventBusImpl = initializationElement("eventBusImpl", Bus.class);
        eventAssembleAnalysis = initializationElement("eventAssembleAnalysisImpl", EventAssembleAnalysis.class);

    }

    @Test
    public void test_1_startBus(){
        eventBusImpl.startBus();
    }

    @Test
    public void test_2_PushEvent(){
        try {
            eventBusImpl.push(Optional.of(getNewEvent()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_PushEvents(){
        try {
            for(int i=0; i<1000; i++){
                eventBusImpl.push(Optional.of(getNewEvent()), Optional.of(TimeUnit.MICROSECONDS), Optional.of(300));
            }

        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_3_pushAsyncEvent(){
        eventBusImpl.pushAsync(Optional.of(getNewEvent()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
    }

    @Test
    public void test_3_pushAsyncEvents(){
        for(int i=0; i<1000; i++){
            eventBusImpl.pushAsync(Optional.of(getNewEvent()), Optional.of(TimeUnit.SECONDS), Optional.of(10));
        }
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
}
