package com.staragzerproject.transaction.factory.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;


/**
 *  @name EventTest
 *  @illustrate Event冒烟测试，会在实际启动的系统中进行功能性测试
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventTest extends BaseJunitTest {

    private static Event event;

    private static EventAssembleAnalysis eventAssembleAnalysis;

    private static String jsonData;

    @Rule
    public ExpectedException expection = ExpectedException.none();

    @Test
    public void test_0_initiation(){
        eventAssembleAnalysis = initializationElement("eventAssembleAnalysisImpl", EventAssembleAnalysis.class);
    }

    @Test
    public void test_1_getInitiationEvent(){
        event = super.initializationElement("standardEvent", Event.class);
        jsonData = event.toString();
    }

    @Test
    public void test_2_InjectEventFromJson(){
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injecrParametersFromJson(Optional.of(jsonData));
    }

    /**
     * @name 测试常规注入参数
     * @illustrate 通过eventAssembleAnalysisHandle.injectEventParameter测试常规注入参数
     * **/
    @Test
    public void test_3_InjectEvent(){
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of("StartSystemTest_test_5_InjectEvent"), Optional.of("test_5_InjectEventValue"));
    }

    @Test
    public void test_4_getEventTimeOut(){
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        System.out.println(event.toString());
        eventAssembleAnalysisHandle.getEventTimeOut();
    }

    @Test
    public void test_5_getEventTimeOutTimeUnit(){
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.getEventTimeOutTimeUnit();
    }
}
