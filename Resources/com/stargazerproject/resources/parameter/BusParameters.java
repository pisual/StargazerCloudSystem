package com.stargazerproject.resources.parameter;

import com.stargazerproject.resources.annotation.Parameters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 核心参数列表 systemParameters
 *  @illustrate 系统所需的systemParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="busParameters")
@Qualifier("busParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="busParameters")
@SuppressWarnings("unused")
public class BusParameters {

	public BusParameters() {}
	
	    //Bus 参数 Start
		/** @name EventBus处理线程的最小值 **/
		private static final String Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MinThreadCount = "2";

		/** @name EventBus处理线程的最大值 **/
		private static final String Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MaxThreadCount = "8";


		/** @name TransactionBus处理线程的最小值 **/
		private static final String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount = "2";

		/** @name TransactionBus处理线程的最大值 **/
		private static final String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount = "8";

		//Bus 参数 End
		
}
