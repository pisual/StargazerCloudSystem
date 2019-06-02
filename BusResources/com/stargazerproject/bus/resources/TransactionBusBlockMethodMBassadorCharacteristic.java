package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
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
 *  @name eventBus BlockMethod MBassador
 *  @illustrate eventBus NoBlockMethod MBassador
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="transactionBusBlockMethodMBassadorCharacteristic")
@Qualifier("transactionBusBlockMethodMBassadorCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusBlockMethodMBassadorCharacteristic implements BusBlockMethod<Transaction>, BaseCharacteristic<BusBlockMethod<Transaction>>{

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

	public TransactionBusBlockMethodMBassadorCharacteristic() {
		super();
		}

	@Override
	public Optional<BusBlockMethod<Transaction>> characteristic() {
		bus = new MBassador(new BusConfiguration()
				.addFeature(Feature.SyncPubSub.Default())
				.addFeature(Feature.AsynchronousHandlerInvocation.Default(minThreadCount(), maxThreadCount()))
				.addFeature(Feature.AsynchronousMessageDispatch.Default())
				.addPublicationErrorHandler(publicationError -> log.ERROR(publicationError, publicationError.getMessage()))
				.setProperty(IBusConfiguration.Properties.BusId, "EventBus"));
		bus.subscribe(transactionBusListener);
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Transaction>> push(Optional<Transaction> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException{
		IMessagePublication iMessagePublication = bus.publishAsync(busEvent, timeout.get(), timeUnit.get());
		wait(iMessagePublication, timeUnit, timeout);
		return Optional.of(new TransactionBusObserver(Optional.of(iMessagePublication)));
	}

	private void wait(IMessagePublication iMessagePublication, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException{
		for(int i=0; i<timeout.get(); i++){
			if(iMessagePublication.isFinished()){
				return;
			}
			else{
				sleep(timeUnit.get());
				continue;
			}
		}
		log.WARN(iMessagePublication, "Event没有在指定时间内完成任务 : BaseEvent Not completed at the specified time");
		throw new BusEventTimeoutException("Event没有在指定时间内完成任务 : BaseEvent Not completed at the specified time : " + iMessagePublication.toString());
	}

	private static int minThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount);
	}

	private static int maxThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount);
	}

	private void sleep(TimeUnit timeUnit){
		try {
			switch (timeUnit) {
				case SECONDS:
					TimeUnit.SECONDS.sleep(1);
					break;
				case MICROSECONDS:
					TimeUnit.MICROSECONDS.sleep(1);
					break;
				case MILLISECONDS:
					TimeUnit.MILLISECONDS.sleep(1);
					break;
				case NANOSECONDS:
					TimeUnit.NANOSECONDS.sleep(1);
					break;
				default:
					log.WARN(timeUnit, "Other attributes are not supported, Will Use Default : SECONDS");
					TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
		}
	}

}
