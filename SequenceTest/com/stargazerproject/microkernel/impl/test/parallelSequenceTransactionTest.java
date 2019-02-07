package com.stargazerproject.microkernel.impl.test;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.BaseJunitTest;
import com.stargazerproject.transaction.Event;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class parallelSequenceTransactionTest extends BaseJunitTest {

    private ParallelSequenceTransaction<Event> sequence;

    private EventAssembleAnalysis eventAssembleAnalysis;

    @Before
    public void init(){
        sequence = BeanContainer.instance().getBean(Optional.of("standardSequence"), ParallelSequenceTransaction.class);
        eventAssembleAnalysis = BeanContainer.instance().getBean(Optional.of("eventAssembleAnalysisImpl"), EventAssembleAnalysis.class);
    }

    @Test
    public void Test_01_creatParallelSequence(){
        sequence.creatParallelSequence();

    }

    @Test
    public void Test_02_addParallelSequence(){
        Event event = BeanContainer.instance().getBean(Optional.of("standardEvent"), Event.class);
        event.eventAssemble(Optional.of(eventAssembleAnalysis)).get().injectEventParameter(Optional.of("Test"), Optional.of("Test"));
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

}
