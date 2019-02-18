package com.stargazerproject.microkernel.impl.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.exception.EventException;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.ResultState;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SequenceTransactionTest extends BaseJunitTest {

    private static SequenceTransaction<Event> sequence;

    private static EventAssembleAnalysis eventAssembleAnalysis;

    private static EventResultAnalysis eventResultAnalysis;

    private static EventResultAnalysisHandle eventResultAnalysisHandle;

    private static EventResultAnalysisHandle eventResultAnalysisHandle2;

    private static Event event;

    private static Event event2;


    @Test
    public void Test_00_init(){
        sequence = BeanContainer.instance().getBean(Optional.of("standardSequence"), SequenceTransaction.class);
        eventAssembleAnalysis = BeanContainer.instance().getBean(Optional.of("eventAssembleAnalysisImpl"), EventAssembleAnalysis.class);
        eventResultAnalysis = BeanContainer.instance().getBean(Optional.of("eventResultAnalysisImpl"), EventResultAnalysis.class);

        event = BeanContainer.instance().getBean(Optional.of("standardEvent"), Event.class);
        event.eventAssemble(Optional.of(eventAssembleAnalysis)).get().injectEventParameter(Optional.of("User"), Optional.of("Felixeiro")).injectEventParameter(Optional.of("Method"), Optional.of("initializationCellsGroupModel"));


        event2 = BeanContainer.instance().getBean(Optional.of("standardEvent"), Event.class);
        event2.eventAssemble(Optional.of(eventAssembleAnalysis)).get().injectEventParameter(Optional.of("User"), Optional.of("Sion")).injectEventParameter(Optional.of("Method"), Optional.of("test_GetCellsGroupIDModel"));
    }

    @Test
    public void Test_01_creatParallelSequence(){
        sequence.creatSequence();

    }

    @Test
    public void Test_02_addParallelSequence(){
        sequence.addSequence(Optional.of(event));
        sequence.addSequence(Optional.of(event2));
    }

    @Test
    public void Test_03_startBlockParallelSequence(){
        try {
            sequence.startBlockSequence();
        } catch (BusEventTimeoutException e) {
            e.printStackTrace();
        } catch (EventException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_04_checkEventResult() {
     eventResultAnalysisHandle = event.eventResult(Optional.of(eventResultAnalysis)).get();
        if(eventResultAnalysisHandle.resultState().get() != ResultState.SUCCESS){
            throw new IllegalStateException("Event 没有完成，现在Event的状态为 " + eventResultAnalysisHandle.resultState().get());
        }
        else {
            System.out.println("Event完成, EventID为: " + event.sequenceID());
        }
    }

    @Test
    public void Test_04_checkEvent2Result() {
        eventResultAnalysisHandle2 = event2.eventResult(Optional.of(eventResultAnalysis)).get();
        if(eventResultAnalysisHandle2.resultState().get() != ResultState.SUCCESS){
            throw new IllegalStateException("Event2 没有完成，现在Event2的状态为 " + eventResultAnalysisHandle2.resultState().get());
        }
        else {
            System.out.println("Event2完成, Event2ID为: " + event2.sequenceID());
        }
    }

}
