package com.stargazerproject.sequence.resources;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.analysis.SequenceTransactionResultAnalysis;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.annotation.Annotations;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.exception.EventException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;
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

@Component(value="sequenceEventsCharacteristic")
@Qualifier("sequenceEventsCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SequenceEventsCharacteristic implements SequenceTransaction<Event>, BaseCharacteristic<SequenceTransaction<Event>> {

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod logMethod;

	@Autowired
	@Qualifier("annotationsImpl")
	private Annotations annotations;

	@Autowired
	@Qualifier("eventBusImpl")
	private BusBlockMethod<Event> eventBus;

	@Autowired
	@Qualifier("aggregateRootCache")
	Cache<String, String> aggregateRootCache;

	@Autowired
	@Qualifier("eventResultAnalysisImpl")
	private EventResultAnalysis eventResultAnalysis;

	@Autowired
	@Qualifier("eventAssembleAnalysisImpl")
	private EventAssembleAnalysis eventAssembleAnalysis;

	@Autowired
	@Qualifier("aggregateRootIndexCache")
	private Cache<String, Cache<String, String>> aggregateRootIndexCache;

	@Autowired
	@Qualifier("sequenceTransactionResultAnalysisImpl")
	private SequenceTransactionResultAnalysis sequenceTransactionResultAnalysis;

	/** @illustrate Sequence ID, 同时也承担者聚合根索引的ID **/
	private String groupID;

	/** @illustrate Event 临时存储列表 **/
	Multimap<String, Event> cache;

	public SequenceEventsCharacteristic(){ }

	@Override
	public Optional<SequenceTransaction<Event>> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<SequenceTransaction<Event>> creatSequence() {
		cache = ArrayListMultimap.create();
		groupID = SequenceUtil.getUUIDSequence();
		aggregateRootIndexCacheInitialization();
		return Optional.of(this);
	}

	@Override
	public void addSequence(Optional<Event> event) {
		cache.put(groupID, event.get());
		aggregateRootIndexCacheEventParameterInject(event.get(), eventAssembleAnalysis);
	}

	@Override
	public void clearSequence() {
		cache.clear();
	}

	@Override
	public Optional<SequenceObserver<Event>> startBlockSequence() throws BusEventTimeoutException, EventException {
		pushEvent(cache.values());
		SequenceObserver<Event> sequenceObserver = new SequenceObserverImpl<Event>(Optional.of(sequenceTransactionResultAnalysis),Optional.of(cache));
		return Optional.of(sequenceObserver);
	}

	@Override
	public Optional<SequenceObserver<Event>> startSequence() {
		startPushThread(cache.values());
		SequenceObserver<Event> sequenceObserver = new SequenceObserverImpl<Event>(Optional.of(sequenceTransactionResultAnalysis),Optional.of(cache));
		return Optional.of(sequenceObserver);
	}

	private void pushEvent(Collection<Event> eventList) throws BusEventTimeoutException, EventException{
		for (Event event : eventList){
			eventBus.push(Optional.of(event));
			ResultState resultState = event.eventResult(Optional.of(eventResultAnalysis)).get().resultState().get();
			if(resultState != ResultState.SUCCESS){
				throw new EventException("Sequence 序列中存在失败的事物 ： " + event.toString());
			}
		}
	}

	private void startPushThread(Collection<Event> eventList){
		new Thread(() -> {
			try{
				pushEvent(cache.values());
			}catch(EventException eventException){
				logMethod.ERROR(this, eventException.getMessage());
			}catch(BusEventTimeoutException busEventTimeoutException){
				logMethod.ERROR(this, busEventTimeoutException.getMessage());
			}
		}).start();
	}

	private void aggregateRootIndexCacheInitialization(){
		aggregateRootIndexCache.put(Optional.of(groupID), Optional.of(aggregateRootCache));
	}

	private void aggregateRootIndexCacheEventParameterInject(Event event, EventAssembleAnalysis eventAssembleAnalysis){
		EventAssembleAnalysisHandle eventAssembleAnalysisHandle = event.eventAssemble(Optional.of(eventAssembleAnalysis)).get();
		eventAssembleAnalysisHandle.injectEventParameter(Optional.of("AggregateRootCacheIndex"), Optional.of(groupID));
	}
}
