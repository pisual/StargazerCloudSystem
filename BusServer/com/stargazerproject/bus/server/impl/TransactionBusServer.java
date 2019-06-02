package com.stargazerproject.bus.server.impl;

import com.stargazerproject.bus.Bus;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="transactionBusServer")
@Qualifier("transactionBusServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusServer implements StanderServiceShell{

	@Autowired
	@Qualifier("transactionBus")
	private StanderCharacteristicShell<Bus<Transaction>> transactionBus;

	@Autowired
	@Qualifier("transactionBusResourcesShell")
	private BaseCharacteristic<Bus<Transaction>> transactionBusResourcesShell;

	/** @construction 初始化构造 **/
	private TransactionBusServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		transactionBus.initialize(transactionBusResourcesShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}