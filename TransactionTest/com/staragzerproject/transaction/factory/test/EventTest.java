package com.staragzerproject.transaction.factory.test;

import com.google.common.base.Optional;
import com.google.gson.JsonSyntaxException;
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


    @Rule
    public ExpectedException expection = ExpectedException.none();

    @Test
    public void test_0_getInitiationEvent(){
        eventAssembleAnalysis = initializationElement("eventAssembleAnalysisImpl", EventAssembleAnalysis.class);
    }

    @Test
    public void test_1_getInitiationEvent(){
        event = super.initializationElement("standardEvent", Event.class);
        System.out.println(event.toString());
    }

    @Test
    public void test_2_InjectEventFromJson(){
        String json = "{\"StartSystemTest\":\"Test1\",\"StartSystemTest2\":\"Test2\"}";
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injecrParametersFromJson(Optional.of(json));
        System.out.println(event.toString());
    }

    /**
     * @name 测试嵌套Json
     * @illustrate eventAssembleAnalysisHandle.injecrParametersFromJson不支持嵌套Json数据
     * @Exception ClassCastException  ErrorMessage：com.google.gson.internal.LinkedTreeMap cannot be cast to java.lang.String
     * **/
    @Test
    public void test_3_InjectEventFromNestJson(){
        expection.expect(ClassCastException.class);
        expection.expectMessage("com.google.gson.internal.LinkedTreeMap cannot be cast to java.lang.String");

        String nestJson = "{\"Test2\":{\"StartSystemTest\":\"Test1\",\"StartSystemTest2\":\"Test1\",\"StartSystemTest3\":\"Test1\",\"StartSystemTest4\":\"Test1\"}}\n";
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injecrParametersFromJson(Optional.of(nestJson));
        System.out.println(event.toString());
    }

    /**
     * @name 测试错误的Json
     * @illustrate eventAssembleAnalysisHandle.injecrParametersFromJson不支持嵌套Json数据
     * @Exception ClassCastException  ErrorMessage：com.google.gson.internal.LinkedTreeMap cannot be cast to java.lang.String
     * **/
    @Test
    public void test_4_InjectEventFromErroeJson(){
        expection.expect(JsonSyntaxException.class);

        String errorJson = "{\"sss,Test2\"，:{\"StartSys，temTest\":\"Tes」」t1\",\"StartSystemTest2\":\"Test1\",\"StartSystemTest3\":\"Test1\",\"StartSystemTest4\":\"Test1\"}}\n";
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injecrParametersFromJson(Optional.of(errorJson));
        System.out.println(event.toString());
    }

    @Test
    public void test_5_InjectEvent(){
        EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
        eventAssembleAnalysisHandle.injectEventParameter(Optional.of("StartSystemTest_test_5_InjectEvent"), Optional.of("test_5_InjectEventValue"));
        System.out.println(event.toString());
    }

}
