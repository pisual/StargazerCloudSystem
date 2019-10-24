package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.bus.resources.shell.EventBusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.Event;
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

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  @name eventBus BlockMethod MBassador
 *  @illustrate eventBus NoBlockMethod MBassador
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="eventBusBlockMethodMBassadorCharacteristic")
@Qualifier("eventBusBlockMethodMBassadorCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusBlockMethodMBassadorCharacteristic implements BusBlockMethod<Event>, BaseCharacteristic<BusBlockMethod<Event>>{

	/** @name Bus处理线程的最小值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MinThreadCount;

	/** @name Bus处理线程的最大值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MaxThreadCount;

	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;

	@Autowired
	@Qualifier("eventBusListenerSynchronously")
	private BusListener<Optional<Event>> eventBusListener;

	private MBassador bus;

	public EventBusBlockMethodMBassadorCharacteristic() {
		super();
		}

	protected static final ThreadFactory MessageHandlerThreadFactory = new ThreadFactory() {

		private final AtomicInteger threadID = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = Executors.defaultThreadFactory().newThread(r);
			thread.setName("AsyncHandler-" + threadID.getAndIncrement());
			thread.setDaemon(true);
			return thread;
		}
	};

	protected static final ThreadFactory MessageDispatchThreadFactory = new ThreadFactory() {

		private final AtomicInteger threadID = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = Executors.defaultThreadFactory().newThread(r);
			thread.setDaemon(true);// do not prevent the JVM from exiting
			thread.setName("Dispatcher-" + threadID.getAndIncrement());
			return thread;
		}
	};

	@Override
	public Optional<BusBlockMethod<Event>> characteristic() {
		bus = new MBassador(new BusConfiguration()
				.addFeature(Feature.SyncPubSub.Default())
				.addFeature(new Feature.AsynchronousHandlerInvocation().setExecutor(new ThreadPoolExecutor(minThreadCount(), maxThreadCount(), 100,
						TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10), MessageHandlerThreadFactory)))
				.addFeature(new Feature.AsynchronousMessageDispatch()
						.setNumberOfMessageDispatchers(2)
						.setDispatcherThreadFactory(MessageDispatchThreadFactory)
						.setMessageQueue(new LinkedBlockingQueue<IMessagePublication>(10)))
				.addPublicationErrorHandler(publicationError ->  log.ERROR("eventBusBlockMethodMBassadorCharacteristic publicationError", publicationError.getMessage()))
				.setProperty(IBusConfiguration.Properties.BusId, "EventBus"));
		bus.subscribe(eventBusListener);
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Event>> push(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException{
		IMessagePublication iMessagePublication = bus.publishAsync(busEvent, timeout.get(), timeUnit.get());
		wait(iMessagePublication, timeUnit, timeout);
		return Optional.of(new EventBusObserver(Optional.of(iMessagePublication)));
	}

	private void wait(IMessagePublication iMessagePublication, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException{
		for(int i=0; i<timeout.get(); i++){
			if(iMessagePublication.isRunning()){
				return;
			}
			else{
				sleep(timeUnit.get());
				continue;
			}
		}
		log.WARN(iMessagePublication, "Event没有在指定时间内开始执行任务 : BaseEvent Not start at the specified time");
		throw new BusEventTimeoutException("Event没有在指定时间内开始执行任务 : BaseEvent Not completed at the specified time : " + iMessagePublication.toString());
	}

	private static int minThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MinThreadCount);
	}

	private static int maxThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MaxThreadCount);
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
