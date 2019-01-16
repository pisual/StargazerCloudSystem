package com.stargazerproject.cache.datastructure.impl;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="aggregateRootCache")
@Qualifier("aggregateRootCache")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public final class AggregateRootCache extends BaseDataStructureCache<String, String>{

	private static final long serialVersionUID = 7876165181093327466L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AggregateRootCache() {}
	
}
