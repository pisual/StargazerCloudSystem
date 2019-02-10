package com.stargazerproject.microkernel.impl.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.ResultState;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class parallelSequenceTransactionTest extends BaseJunitTest {

    private static ParallelSequenceTransaction<Event> sequence;

    private static EventAssembleAnalysis eventAssembleAnalysis;

    private static EventResultAnalysis eventResultAnalysis;

    private static EventResultAnalysisHandle eventResultAnalysisHandle;

    private static Event event;


    @Test
    public void Test_00_init(){
        sequence = BeanContainer.instance().getBean(Optional.of("standardSequence"), ParallelSequenceTransaction.class);
        eventAssembleAnalysis = BeanContainer.instance().getBean(Optional.of("eventAssembleAnalysisImpl"), EventAssembleAnalysis.class);
        eventResultAnalysis = BeanContainer.instance().getBean(Optional.of("eventResultAnalysisImpl"), EventResultAnalysis.class);

        event = BeanContainer.instance().getBean(Optional.of("standardEvent"), Event.class);
        event.eventAssemble(Optional.of(eventAssembleAnalysis)).get().injectEventParameter(Optional.of("User"), Optional.of("Felixeiro"));
    }

    @Test
    public void Test_01_creatParallelSequence(){
        sequence.creatParallelSequence();

    }

    @Test
    public void Test_02_addParallelSequence(){
        sequence.addParallelSequence(Optional.of(event));
    }

    @Test
    public void Test_03_startBlockParallelSequence(){
        try {
            sequence.startBlockParallelSequence();
        } catch (BusEventTimeoutException e) {
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

}
