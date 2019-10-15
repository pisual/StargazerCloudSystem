package com.stargazerproject.serializable.server.impl;

import com.stargazerproject.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.serializable.Serializables;
import com.stargazerproject.service.baseinterface.StanderServiceShell;

/** 
 *  @name StandardSequenceServer 服务的实现
 *  @illustrate 继承于ServiceShell的StandardSequenceServer相关服务实现
 *  @author Felixerio
 *  **/
@Component(value="serializableServer")
@Qualifier("serializableServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SerializableServer implements StanderServiceShell{
	
	@Autowired
	@Qualifier("transactionTransmissionSerializables")
	private StanderCharacteristicShell<Serializables<Transaction, byte[]>> networkTransmissionSerializables;
	
	@Autowired
	@Qualifier("transactionTransmissionSerializablesShell")
	private BaseCharacteristic<Serializables<Transaction, byte[]>> networkTransmissionSerializablesShell;
	
	/** @construction 初始化构造 **/
	private SerializableServer() {}
	
	/** @illustrate 启动服务及相关操作 **/
	@Override
	public void startUp() {
		networkTransmissionSerializables.initialize(networkTransmissionSerializablesShell.characteristic());
	}

	/** @illustrate 关闭服务及相关操作 **/
	@Override
	public void shutDown() {
	}
	
}