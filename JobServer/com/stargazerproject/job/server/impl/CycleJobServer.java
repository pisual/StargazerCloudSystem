package com.stargazerproject.job.server.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.job.Job;
import com.stargazerproject.job.JobObserver;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Component(value="cycleJobServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("cycleJobServer")
public class CycleJobServer implements StanderServiceShell{

	@Autowired
	@Qualifier("cycleJob")
	private StanderCharacteristicShell<Job<ScheduledExecutorService, JobObserver>> characteristic;

	@Autowired
	@Qualifier("cycleJobShell")
	private BaseCharacteristic<Job<ScheduledExecutorService, JobObserver>> shell;

	private Job<ScheduledExecutorService, JobObserver> shellimpl;

	/**
	* @name Springs使用的初始化构造
	* @illustrate
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private CycleJobServer() {}

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public CycleJobServer(Optional<StanderCharacteristicShell<Job<ScheduledExecutorService, JobObserver>> > characteristicArg, Optional<BaseCharacteristic<Job<ScheduledExecutorService, JobObserver>>> shellArg) {
		characteristic = characteristicArg.get();
		shell = shellArg.get();
	}
	
	@Override
	public void startUp() {
		shellimpl= shell.characteristic().get();
		shellimpl.startJobEngine();
		characteristic.initialize(Optional.of(shellimpl));
	}

	@Override
	public void shutDown() {
		shellimpl.stopJobEngine();
	}

}
