package com.stargazerproject.transaction.impl.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.*;
import com.stargazerproject.analysis.handle.EventAssembleAnalysisHandle;
import com.stargazerproject.analysis.handle.EventExecuteAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultAnalysisHandle;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.*;
import com.stargazerproject.transaction.base.impl.ID;
import com.stargazerproject.transaction.exception.EventSkipException;
import com.stargazerproject.util.CloneUtil;
import com.stargazerproject.util.JsonUtil;
import com.stargazerproject.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 事件（BaseEvent）实现
 *  @illustrate 事件（BaseEvent）是事务的原子单位，一个事件包含了一个事务，并包含了这个事务所需的全部参数
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseEventShell")
@Qualifier("baseEventShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public class BaseEventShell extends ID implements Event, BaseCharacteristic<Event>{

	/** @illustrate 日志接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod logMethod;

	/** @illustrate 参数缓存 **/
	@Autowired
	@Qualifier("eventInteractionCache")
	private Cache<String, String> parametersCache;

	/** @illustrate Event Result Shell 实例**/
	@Autowired
	@Qualifier("baseEventResultShell")
	private BaseCharacteristic<EventResults> baseEventResultShell;

    @Autowired
    @Qualifier("eventResultsAssembleAnalysisImpl")
    private EventResultsAssembleAnalysis eventResultsAssembleAnalysis;

	/** @illustrate eventResultRecordAnalysis实例**/
	@Autowired
	@Qualifier("eventResultsExecuteAnalysisImpl")
	private EventResultsExecuteAnalysis eventResultsExecuteAnalysis;

	@Autowired
	@Qualifier("eventResultsResultAnalysisImpl")
	private EventResultsResultAnalysis eventResultsResultAnalysis;


	private EventResults eventResults;

	/** @illustrate 事件状态, 初始状态为初始态**/
	private EventState eventState = EventState.INIT;


	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventShell(Optional<EventResults> resultArg, Optional<Cache<String, String>> parametersCacheArg, Optional<LogMethod> logMethodArg){
		eventResults = resultArg.get();
		logMethod = logMethodArg.get();
		parametersCache = parametersCacheArg.get();
	}

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BaseEventShell() {}
	
	@Override
	public Optional<Event> characteristic() {
		eventResults = baseEventResultShell.characteristic().get();
		return Optional.of(this);
	}
	
	/** @illustrate 事件生产，生产者调用
	 *  @param      :EventAssembleAnalysis eventAssembleAnalysis : 事件生产分析器接口
	 * **/
	@Override
	public Optional<EventAssembleAnalysisHandle> eventAssemble(Optional<EventAssembleAnalysis> eventAssembleAnalysis){
		if(eventState != EventState.INIT && eventState != EventState.WAIT){
			logMethod.ERROR(this, "Event无法构建，因为Event状态不为Init（初始状态）或者 Wait（等待状态），现在Event的状态为：" + eventState);
			throw new IllegalStateException("Event无法构建，因为Event状态不为Init（初始状态）或者 Wait（等待状态），现在Event的状态为：" + eventState);
		}
		else if(eventState == EventState.INIT){
			this.injectSequenceID(Optional.of(SequenceUtil.getUUIDSequence()));
			eventState = EventState.WAIT;
		}
		return eventAssembleAnalysis.get().analysis(Optional.of(parametersCache), eventResults.resultAssemble(Optional.of(eventResultsAssembleAnalysis)));
	}

	/** @illustrate 开始执行事件, 执行者调用 执行者只有在Event处于EventState.WAIT的状态下才会启动事件运行分析接口，<P>
	 *              如果Event处于EventState.PASS，将快速失败此事务
	 * 	@param      :EventExecuteAnalysis eventAnalysis : 事件运行器接口
	 * **/
	@Override
	public Optional<EventExecuteAnalysisHandle> eventExecute(Optional<EventExecuteAnalysis> eventAnalysis) {
		EventExecuteAnalysisHandle eventExecuteAnalysisHandle = eventAnalysis.get().analysis(Optional.of(parametersCache), Optional.of(eventState), eventResults.resultsExecute(Optional.of(eventResultsExecuteAnalysis))).get();
		if(EventState.WAIT == eventState){

		}
		else if(EventState.PASS == eventState){
			logMethod.INFO(this, "Evenr无法启动，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState + " ,事件处于PASS状态，将快速失败此事务");
			throw new IllegalStateException("Evenr无法启动，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState + " ,事件处于PASS状态，将快速失败此事务");
		}
		else if(EventState.INIT == eventState || EventState.COMPLETE == eventState || EventState.RUN == eventState){
			logMethod.ERROR(this, "Evenr无法启动，因为Event状态不正常，现在Event的状态为：" + eventState);
			throw new IllegalStateException("Evenr无法启动，因为Event状态不正常，现在Event的状态为：" + eventState);
		}
		return Optional.of(eventExecuteAnalysisHandle);
	}
	
	/** @illustrate 分析事件结果，分析者调用
	 *              分析者不受EventState状态的约束
	 *  @param      eventResultAnalysis eventResultAnalysis : 事件结果分析器接口
	 * **/
	@Override
	public Optional<EventResultAnalysisHandle> eventResult(Optional<EventResultAnalysis> eventResultAnalysis){
		return eventResultAnalysis.get().analysis(Optional.of(parametersCache), eventResults.resultsResult(Optional.of(eventResultsResultAnalysis)));
	}
	
	/** @illustrate  跳过此事件
	 *  @exception : 如果Event状态不为Wait（等待执行状态），将抛出IllegalStateException异常，并报告现在的Event状态
	 * **/
	@Override
	public void skipEvent(Optional<String> skipCause){
		if(eventState != EventState.WAIT){
			logMethod.ERROR(this, "Evenr无法跳过，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
			throw new IllegalStateException("Evenr无法跳过，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
		}
		else{
			eventState = EventState.PASS;
			EventResultsExecuteAnalysisHandle EventResultsExecuteAnalysisHandle = eventResults.resultsExecute(Optional.of(eventResultsExecuteAnalysis)).get();
			EventResultsExecuteAnalysisHandle.errorMessage(Optional.of(new EventSkipException("Event Pass, Cause: " + skipCause.get())));
			EventResultsExecuteAnalysisHandle.EventResultState(Optional.of(EventResultState.FAULT));
		}
	}
	
	/** @illustrate 获取事件状态 ,返回一个经过深度拷贝的EventState对象
	 *  @return     Optional<EventState> : 结果状态 EventState
	 * **/
	@Override
	public Optional<EventState> eventState(){
		EventState copyEventState = (EventState)CloneUtil.deepClone(Optional.of(eventState));
		return Optional.of(copyEventState);
	}
	
	@Override
	public String toString() {
		StringBuffer jsonResult = new StringBuffer()
					 .append("{")
				     .append(JsonUtil.cacheToJson(Optional.of(parametersCache), Optional.of("eventInteractionCache")))
					 .append(",")
				     .append(eventResults.toString())
					 .append(",")
				     .append(JsonUtil.StringToJson(Optional.of("EventState"), Optional.of(eventState.toString())))
				     .append("}");
		return jsonResult.toString();
	}


}
