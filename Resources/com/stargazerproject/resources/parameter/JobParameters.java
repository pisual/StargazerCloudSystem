package com.stargazerproject.resources.parameter;

import com.stargazerproject.resources.annotation.Parameters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/** 
 *  @name 核心参数列表 systemParameters
 *  @illustrate 系统所需的systemParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="jobParameters")
@Qualifier("jobParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="jobParameters")
@SuppressWarnings("unused")
public class JobParameters {

	public JobParameters() {}

		/** @name Job线程池中线程的数量 **/
		private static final String Parameters_Module_Kernel_Job_ThreadPool_Size = "3";

		private static String Parameters_Module_Kernel_Job_AwaitTermination_Time = "10";

		/** @name 结束Job的时候等待的最长时间Unir **/
		private static String Parameters_Module_Kernel_Job_AwaitTermination_TimeUnit = TimeUnit.SECONDS.toString();
		
}
