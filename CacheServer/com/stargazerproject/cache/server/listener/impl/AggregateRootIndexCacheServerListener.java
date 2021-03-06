package com.stargazerproject.cache.server.listener.impl;

import com.google.common.util.concurrent.Service.State;
import com.stargazerproject.service.base.impl.StandardWorkInServiceListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="aggregateRootIndexCacheServerListener")
@Qualifier("aggregateRootIndexCacheServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AggregateRootIndexCacheServerListener extends StandardWorkInServiceListener{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootIndexCacheServerListener() {
		super();
	}
	
	@Override
	public void starting() {
		super.starting();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : aggregateRootIndexCache Service Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : aggregateRootIndexCache Service Run");
	}
	
	/** @illustrate 开始停止服务 **/
	@Override
	public void stopping(State from) {
		super.stopping(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : aggregateRootIndexCache Service Stopping");
	}
	
	/** @illustrate 服务停止 **/
	@Override
	public void terminated(State from) {
		super.terminated(from);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : aggregateRootIndexCache Service Terminated");
	}
	
	/** @illustrate 服务失败 **/
	@Override
	public void failed(State from, Throwable failure) {
		super.failed(from, failure);
		baseLog.INFO(this, "Stargazer ServiceControlSystem Report : aggregateRootIndexCache Service Failed");
	}
	
}
