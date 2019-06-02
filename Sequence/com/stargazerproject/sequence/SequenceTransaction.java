package com.stargazerproject.sequence;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.exception.SequenceTimeOutException;

/** 
 *  @name 顺序序列（单向序列）的事务接口
 *  @illustrate 
 *            名词解析：
 *                     事务组：顺序序列的事务载体，默认完全的的实现是Transaction，所以，以下的事务组默认为Transaction
 *                     事务：  事务组（Transaction）内部包含的一个事务
 *                     
 *              顺序序列的执行方法：
 *                  顺序序列是单次运行方法，首先创建序列，并为序列加入事务组（Transaction），阻塞序列一经启动，首先会关闭序列，禁止任何对队列的操作，
 *                  随后，严格按照序列顺序，依次执行序列方法事务组，一旦其中任何一个事务执行出现异常，会立即终止序列的继续运行并返回相应结果。
 *                  一旦此次序列执行完毕，会清空并结束序列。
 *              
 *              序列的顺序处理方法为：
 *                  依赖 ：SequenceBus
 *                      
 *                  Sequence : { Transaction（"Sequence a","Sequence b","Sequence c"）- Transaction（"Sequence d","Sequence e","Sequence f"）}
 *                      
 *                  首先，会取出Sequence中的第一个事务，推入SequenceBus，确认执行完毕并且成功后再取出第二个推入SequenceBus，依次完成。
 *             
 *             顺序序列的使用范围：
 *                 需要严格依赖前者执行的事务
 *                     
 *                     
 *  @param <K> 序列事务组类型
 *  @author Felixerio
 *  **/
public interface SequenceTransaction<K>{
	
	/**
	* @name 启动顺序序列
	* @illustrate 启动指定的Sequence队列，并阻塞，直到序列全部完成后返回SequenceObserver结果
	* @exception SequenceTimeOutException : 超时会抛出SequenceTimeOutException异常
	* **/
	public Optional<SequenceObserver<K>> startBlockSequence(Optional<K> transaction) throws SequenceTimeOutException;
	
	/**
	* @name 启动顺序序列
	* @illustrate 启动指定的Sequence队列，非阻塞，执行后立即返回SequenceObserver结果观察对象，可以根据SequenceObserver来判断
	*             序列是否完成
	* **/
	public Optional<SequenceObserver<K>> startSequence(Optional<K> transaction);
}
