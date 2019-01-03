package com.stargazerproject.transaction;

/** 
 *  @name 结果（Result）接口
 *  @illustrate 实现结果的基础功能
 *  @author Felixerio
 *  @version 1.1.0
 *  **/
public interface Result<Handle> extends ResultResult<Handle>, ResultRecord, ValueObject<Result>{

}
