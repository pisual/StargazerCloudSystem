package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.resources.shell.EventBusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.Event;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  @name eventBus NoBlockMethod RxJava版本
 *  @illustrate eventBus NoBlockMethod RxJava版本
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="eventBusAsyncMethodMBassadorCharacteristic")
@Qualifier("eventBusAsyncMethodMBassadorCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusAsyncMethodMBassadorCharacteristic implements BusAsyncMethod<Event>, BaseCharacteristic<BusAsyncMethod<Event>>{

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
	@Qualifier("eventBusListenerAsynchronously")
	private BusListener<Optional<Event>> eventBusListener;

	private MBassador bus;

	public EventBusAsyncMethodMBassadorCharacteristic() {
		super();
		}

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


	static final IPublicationErrorHandler illustrativeHandler =  new IPublicationErrorHandler() {
		@Override
		public void handleError(PublicationError error) {

			System.out.println(error.getMessage()); // An error message to describe what went wrong
			System.out.println(error.getCause()); // The underlying exception
			System.out.println(error.getPublishedMessage()); // The message that was published (can be null)
			System.out.println(error.getListener()); // The listener that was invoked when the execption was thrown (can be null)
			System.out.println(error.getHandler()); // The message handler (Method) that was invoked when the execption was thrown (can be null)
		}
	};

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

	@Override
	public Optional<BusAsyncMethod<Event>> characteristic() {
		bus = new MBassador(new BusConfiguration()
				.addFeature(Feature.SyncPubSub.Default())
				.addFeature(new Feature.AsynchronousHandlerInvocation().setExecutor(new ThreadPoolExecutor(minThreadCount(), maxThreadCount(), 100,
						TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100), MessageHandlerThreadFactory)))
				.addFeature(new Feature.AsynchronousMessageDispatch()
						.setNumberOfMessageDispatchers(2)
						.setDispatcherThreadFactory(MessageDispatchThreadFactory)
						.setMessageQueue(new LinkedBlockingQueue<IMessagePublication>(2)))
				.addPublicationErrorHandler(illustrativeHandler)
				.setProperty(IBusConfiguration.Properties.BusId, "EventBus"));
		bus.subscribe(eventBusListener);
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Event>> pushAsync(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) {
		IMessagePublication iMessagePublication = bus.publishAsync(busEvent, timeout.get(), timeUnit.get());
		return Optional.of(new EventBusObserver(Optional.of(iMessagePublication)));
	}

	private static int minThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MinThreadCount);
	}

	private static int maxThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_EventBus_MBassador_HandlerInvocation_MaxThreadCount);
	}
}
