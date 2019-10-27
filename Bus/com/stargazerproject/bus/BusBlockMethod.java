package com.stargazerproject.bus;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;

/** 
 *  @name 总线阻塞方法
 *  @illustrate 总线阻塞方法
 *  @param <T>  总线消息德类型
 *  @author Felixerio
 *  **/
public interface BusBlockMethod<T, E extends Exception> {

	/**
	* @name 置入
	* @illustrate 阻塞置入方法，将阻塞直到结果返回
	* @param busEvent：指令，timeUnit：等待的时间单位， timeout：等待的时间长度
	* @return 返回指令
	* **/
	public Optional<BusObserver<T, E>> push(Optional<T> busEvent) throws E;
	
}
