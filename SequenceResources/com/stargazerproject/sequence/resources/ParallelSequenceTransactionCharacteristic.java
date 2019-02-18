package com.stargazerproject.sequence.resources;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.SequenceTransactionResultAnalysis;
import com.stargazerproject.annotation.Annotations;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.exception.EventException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.base.impl.SequenceObserverImpl;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.ResultState;
import com.stargazerproject.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component(value="parallelSequenceTransactionCharacteristic")
@Qualifier("parallelSequenceTransactionCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ParallelSequenceTransactionCharacteristic implements ParallelSequenceTransaction<Event> , BaseCharacteristic<ParallelSequenceTransaction<Event>> {

    /** @illustrate 获取Log(日志)接口 **/
    @Autowired
    @Qualifier("logRecord")
    private LogMethod logMethod;

    @Autowired
    @Qualifier("eventBusImpl")
    private BusNoBlockMethod<Event> eventNoBlockBus;

    @Autowired
    @Qualifier("eventResultAnalysisImpl")
    private EventResultAnalysis eventResultAnalysis;


    @Autowired
    @Qualifier("sequenceTransactionResultAnalysisImpl")
    private SequenceTransactionResultAnalysis sequenceTransactionResultAnalysis;

    @Autowired
    @Qualifier("annotationsImpl")
    private Annotations annotations;

    /** @illustrate Event 临时存储列表 **/
    Multimap<String, Event> cache;

    private String groupID;

    public ParallelSequenceTransactionCharacteristic(){ }

    @Override
    public Optional<ParallelSequenceTransaction<Event>> characteristic() {
        return Optional.of(this);
    }

    @Override
    public Optional<ParallelSequenceTransaction<Event>> creatParallelSequence() {
        cache = ArrayListMultimap.create();
        groupID = SequenceUtil.getUUIDSequence();
        return Optional.of(this);
    }

    @Override
    public void addParallelSequence(Optional<Event> event) {
        cache.put(groupID, event.get());
    }


    @Override
    public void clearParallelSequence() {
        cache.clear();
    }

    @Override
    public Optional<SequenceObserver<Event>> startBlockParallelSequence() throws BusEventTimeoutException, EventException {
        pushEvent(cache.values());
        checkEventResult(cache.values());
        return Optional.of(getSequenceObserver());
    }

    @Override
    public Optional<SequenceObserver<Event>> startParallelSequence() {
        pushEvent(cache.values());
        return Optional.of(getSequenceObserver());
    }

    @Override
    public Optional<SequenceObserver<Event>> shutDownParallelSequence() {
        chancleAllEvents(cache.values());
        return Optional.of(getSequenceObserver());
    }

    private SequenceObserver<Event> getSequenceObserver(){
        return new SequenceObserverImpl<Event>(Optional.of(sequenceTransactionResultAnalysis),Optional.of(cache));
    }

    private void chancleAllEvents(Collection<Event> eventList){
        for (Event event : eventList){
            event.skipEvent();
        }
    }

    private void pushEvent(Collection<Event> eventList){
        for (Event event : eventList){
            eventNoBlockBus.pushNoBlock(Optional.of(event));
        }
    }

    private void checkEventResult(Collection<Event> eventList) throws BusEventTimeoutException, EventException{

        for (int i = 0; i <=100; i++) {

            if(i == 100){
                throw new  BusEventTimeoutException("Event time out : " + this.toString());
            }
            int successsNum = eventList.size();
            for (Event event : eventList){
                ResultState resultState = event.eventResult(Optional.of(eventResultAnalysis)).get().resultState().get();
                if(resultState == ResultState.WAIT){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                else if(resultState == ResultState.FAULT){
                    throw new EventException("Event Run Fault, Event : " + event.toString());
                }
                else if(resultState == ResultState.SUCCESS){
                    successsNum--;
                    if(successsNum == totalSuccessNum()){
                        return;
                    }
                }
            }
        }

    }

    private int totalSuccessNum(){
        return 0;
    }

}
