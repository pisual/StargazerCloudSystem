package com.stargazerproject.cell.impl;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.annotation.description.EventBaseParameters;
import com.stargazerproject.annotation.description.EventConfiguration;
import com.stargazerproject.annotation.description.EventFailureStrategy;
import com.stargazerproject.annotation.description.EventRunStrategy;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.base.impl.BaseCellsTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component(value="standardCellsTransactionImpl")
@Qualifier("standardCellsTransactionImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@EventBaseParameters(name = "name", cellsMethodName = " cellsName", CellsMethodNeedParameters = "id. StartPage, EndPage")
@EventConfiguration( name = "name",
					 waitTimeoutUnit = TimeUnit.MILLISECONDS,
					 waitTimeout = 300,
					 runTimeoutUnit = TimeUnit.MILLISECONDS,
					 runTimeout = 500,
					 eventRunStrategy = EventRunStrategy.Single,
					 eventFailureStrategy = EventFailureStrategy.Rollback,
					 retryCount = 1)
public class StandardCellsTransactionImpl extends BaseCellsTransaction<String, String>{

	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	/**
	 * @name 注入方法
	 * @illustrate 注入的方法
	 * @param <Cache<String, String>> 聚合根，不同的方法通过聚合根缓存共享数据
	 * @param <V> 缓存的Value值
	 * **/
	public boolean method(Optional<Cache<String, String>> cache) {
		System.out.println("局外加载成功");
		return Boolean.TRUE;
	}
	
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		System.out.println("事务包裹fallBack");
		return Boolean.TRUE;
    }

}
