package com.stargazerproject.cell.method.sequence;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.analysis.handle.EventResultsExecuteAnalysisHandle;
import com.stargazerproject.annotation.description.Event;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.impl.CellsTransactionImpl;
import com.stargazerproject.cell.method.exception.RunException;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 创建基础节点
 *  @illustrate Cells生成UUID序列
 *  @author Felixerio
 *  **/
@Component(value="createBaseNodeModel")
@Qualifier("createBaseNodeModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateBaseNodeModel extends CellsTransactionImpl {
	
	/** @name 根路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_RootPath;
	
	/** @name 新生区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_EdenNodePath;
	
	/** @name 建组区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_ZoneNodePath;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	public CreateBaseNodeModel() { 
		super(); 
		}

	public Event eventAnnotation(){
		return this.getClass().getAnnotation(Event.class);
	}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "createBaseNodeModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="createBaseNodeModel", 
	                threadPoolKey = "createBaseNodeModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public void method(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle) {
		try {
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_RootPath));
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_EdenNodePath));
			creatPersistentNode(Optional.of(Kernel_Negotiate_BasePath_ZoneNodePath));
			success();
		} catch (Exception e) {
			throw new RunException(e.getMessage());
		}
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param : Optional<Cache<String, String>> cache
	* @param : Throwable throwable
	* **/
	@Override
	public void fallBack(Optional<Cache<String, String>> interactionCache, Optional<EventResultsExecuteAnalysisHandle> eventResultsExecuteAnalysisHandle, Throwable throwable){
		super.fallBack(interactionCache, eventResultsExecuteAnalysisHandle, throwable);
    }
	
	private void creatPersistentNode(Optional<String> path) throws Exception{
		if(nodeNegotiate.checkNodeExists(Optional.of(""), path) != Boolean.TRUE){
			nodeNegotiate.creatPersistentNode(Optional.of(""), path, Optional.absent());
			log.INFO(this, "Create Ephemeral Node : " + path.get());
		}
		else{
			log.WARN(this, "Create Ephemeral Node Error, The Node Already Exists : " + path.get());
		}
	}
	
}
