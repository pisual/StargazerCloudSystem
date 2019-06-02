package com.stargazerproject.bus.server.manage;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.MoreExecutors;
import com.stargazerproject.service.annotation.ServiceZone;
import com.stargazerproject.service.annotation.Services;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/** 
 *  @name StandardServerManage服务集中托管
 *  @illustrate CellsGenerate服务集中托管，继承于Guava的AbstractIdleService
 *  @author Felixerio
 *  **/
@Component(value="transactionBusServerManage")
@Qualifier("transactionBusServerManage")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Services(name="transactionBusServerManage", serviceZone = ServiceZone.System, layer = 12)
public class TransactionBusServerManage extends AbstractIdleService{

	/** @illustrate standardSequenceServer的ServiceShell接口 **/
	@Autowired
	@Qualifier("transactionBusServer")
	private StanderServiceShell standardSequenceServer;

	@Autowired
	@Qualifier("transactionBusServerListener")
	private Listener workInServiceControlListener;

	/** @construction 初始化构造 **/
	public TransactionBusServerManage() {}
	
	/** @illustrate 类完成加载后将自动加载监听器 **/
	@PostConstruct
	private void addListener(){
		addListener(workInServiceControlListener, MoreExecutors.directExecutor());
	}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	protected void startUp() throws Exception {
		standardSequenceServer.startUp();
	}
	
	/** @illustrate 关闭服务及相关操作 **/
	@Override
	protected void shutDown() throws Exception {
		standardSequenceServer.shutDown();
	}
}