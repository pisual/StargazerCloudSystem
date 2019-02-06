package com.stargazerproject.resources.parameter;

import com.stargazerproject.resources.annotation.Parameters;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  @name 核心参数列表 TransactionParameters
 *  @illustrate 系统所需的TransactionParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="transactionParameters")
@Qualifier("transactionParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="transactionParameters")
public class TransactionParameters {

}
