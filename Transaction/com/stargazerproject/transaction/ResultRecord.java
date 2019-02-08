package com.stargazerproject.transaction;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.ThreadSafeLevel;
import com.stargazerproject.annotation.description.ThreadSafeMethodsLevel;

/** 
 *  @name Result记录接口
 *  @illustrate 记录结果相关的功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface ResultRecord {

	/** @illustrate 记录异常消息 
	 *  @param	Optional<String> errorMessage，错误信息，不可以为空值
	 *  @param  Optional<Exception> exception，异常类信息，可以为空值
	 *  	 *  @ThreadSafeMethodsLevel errorMessage的线程安全级别为ThreadSafeLevel.ThreadSafe，线程安全
	 * **/
	@ThreadSafeMethodsLevel(threadSafeLevel = ThreadSafeLevel.ThreadSafe)
	public Optional<ResultRecord> errorMessage(Optional<String> errorMessage, Optional<Exception> exception);

}
