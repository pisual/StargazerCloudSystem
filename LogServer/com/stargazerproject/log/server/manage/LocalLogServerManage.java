package com.stargazerproject.log.server.manage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.MoreExecutors;
import com.stargazerproject.service.annotation.ServiceZone;
import com.stargazerproject.service.annotation.Services;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name OrderCache服务集中托管
 *  @illustrate OrderCache服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="localLogServerManage")
@Qualifier("localLogServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(name="localLogServerManage", serviceZone = ServiceZone.System, layer = 1)
public class LocalLogServerManage extends AbstractIdleService{
	
	/** @illustrate orderCacheServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("localLogServer")
	private StanderServiceShell LocalLogServer;
	
	@Autowired
	@Qualifier("localLogServerListener")
	private Listener workInServiceControlListener;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private LocalLogServerManage() {
		super();
	}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LocalLogServerManage(Optional<StanderServiceShell> LocalLogServerArg, Optional<Listener> workInServiceControlListenerArg) {
		LocalLogServer = LocalLogServerArg.get();
		workInServiceControlListener = workInServiceControlListenerArg.get();
	}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		LocalLogServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		LocalLogServer.shutDown();
	}

}
