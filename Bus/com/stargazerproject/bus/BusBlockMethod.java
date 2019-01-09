package com.stargazerproject.bus;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

import java.util.concurrent.TimeUnit;

/** 
 *  @name 总线阻塞方法
 *  @illustrate 总线阻塞方法
 *  @param <T>  总线消息德类型
 *  @author Felixerio
 *  **/
public interface BusBlockMethod<T> {

	/**
	* @name 置入
	* @illustrate 阻塞置入方法，将阻塞直到结果返回
	* @param <T> 指令， TimeUnit：寻轮的间隔时间 Integer寻轮的次数
	* @return 返回指令
	* **/
	public Optional<T> push(Optional<T> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout)throws BusEventTimeoutException;

	/**
	 * @name 置入
	 * @illustrate 阻塞置入方法，将阻塞直到结果返回，将根据Event的内部注入方法制定间隔时间
	 * @param <T> 指令
	 * @return 返回指令
	 * **/
	public Optional<T> push(Optional<T> busEvent)throws BusEventTimeoutException;
	
}
