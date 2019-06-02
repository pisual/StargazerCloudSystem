package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.resources.shell.TransactionBusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.Transaction;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *  @name eventBus NoBlockMethod RxJava版本
 *  @illustrate eventBus NoBlockMethod RxJava版本
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="transactionBusAsyncMethodMBassadorCharacteristic")
@Qualifier("transactionBusAsyncMethodMBassadorCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusAsyncMethodMBassadorCharacteristic implements BusAsyncMethod<Transaction>, BaseCharacteristic<BusAsyncMethod<Transaction>>{

	/** @name Bus处理线程的最小值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount;

	/** @name Bus处理线程的最大值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount;

	@Autowired
	@Qualifier("logRecord")
	protected static LogMethod log;

	@Autowired
	@Qualifier("transactionBusListener")
	private BusListener<Optional<Transaction>> transactionBusListener;

	private static MBassador bus;

	public TransactionBusAsyncMethodMBassadorCharacteristic() {
		super();
		}

	@Override
	public Optional<BusAsyncMethod<Transaction>> characteristic() {
		bus = new MBassador(new BusConfiguration()
				.addFeature(Feature.SyncPubSub.Default())
				.addFeature(Feature.AsynchronousHandlerInvocation.Default(minThreadCount(), maxThreadCount()))
				.addFeature(Feature.AsynchronousMessageDispatch.Default())
				.addPublicationErrorHandler(publicationError -> log.ERROR(publicationError, publicationError.getMessage()))
				.setProperty(IBusConfiguration.Properties.BusId, "TransactionBus"));
		bus.subscribe(transactionBusListener);
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Transaction>> pushAsync(Optional<Transaction> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		IMessagePublication iMessagePublication = bus.publishAsync(busEvent, timeout.get(), timeUnit.get());
		return Optional.of(new TransactionBusObserver(Optional.of(iMessagePublication)));
	}

	private static int minThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount);
	}

	private static int maxThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount);
	}
}
