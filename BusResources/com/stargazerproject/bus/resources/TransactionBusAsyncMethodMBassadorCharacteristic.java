package com.stargazerproject.bus.resources;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransactionExecuteAnalysis;
import com.stargazerproject.analysis.TransactionResultAnalysis;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.bus.BusAsyncMethod;
import com.stargazerproject.bus.BusListener;
import com.stargazerproject.bus.BusObserver;
import com.stargazerproject.bus.exception.BusTransactionTimeoutException;
import com.stargazerproject.bus.resources.shell.TransactionBusObserver;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.Transaction;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
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
@Component(value="transactionBusAsyncMethodMBassadorCharacteristic")
@Qualifier("transactionBusAsyncMethodMBassadorCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionBusAsyncMethodMBassadorCharacteristic implements BusAsyncMethod<Transaction, BusTransactionTimeoutException>, BaseCharacteristic<BusAsyncMethod<Transaction, BusTransactionTimeoutException>>{

	/** @name Bus处理线程的最小值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount;

	/** @name Bus处理线程的最大值 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount;

	/** @name 异步处理器中当线程的数量大于内核时，多余的空闲线程在终止之前等待新任务的最大时间。 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousHandlerInvocation_keepAliveTime;

	/** @name 异步处理器队列的最大容量。 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousHandlerInvocation_QueueMaxNumber;

	/** @name 消息调度器的线程数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousMessageDispatch_NumberOfMessageDispatchers;

	/** @name 缓存队列的最大数目 **/
	@NeedInject(type="SystemParametersCache")
	private static String Parameters_Module_Kernel_Bus_TransactionBus_MBassador_MessageQueue;


	@Autowired
	@Qualifier("logRecord")
	protected static LogMethod log;

	@Autowired
	@Qualifier("transactionBusListener")
	private BusListener<Optional<Transaction>> transactionBusListener;

	@Autowired
	@Qualifier("transactionResultAnalysisImpl")
	private TransactionResultAnalysis transactionResultAnalysis;

	@Autowired
	@Qualifier("transactionExecuteAnalysisImpl")
	private TransactionExecuteAnalysis transactionExecuteAnalysis;

	@Autowired
	@Qualifier("transactionBusIPublicationErrorHandler")
	private IPublicationErrorHandler transactionBusIPublicationErrorHandler;

	@Autowired
	@Qualifier("transactionBusListenerAsynchronously")
	private BusListener<Optional<Transaction>> transactionBusListenerAsynchronously;

	private static MBassador bus;

	public TransactionBusAsyncMethodMBassadorCharacteristic() {
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
	public Optional<BusAsyncMethod<Transaction, BusTransactionTimeoutException>> characteristic() {
		bus = new MBassador(new BusConfiguration()
				.addFeature(Feature.SyncPubSub.Default())
				.addFeature(new Feature.AsynchronousHandlerInvocation().setExecutor(
						new ThreadPoolExecutor( minThreadCount(),
								maxThreadCount(),
								Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousHandlerInvocation_keepAliveTime),
								TimeUnit.SECONDS,
								new LinkedBlockingQueue<Runnable>(Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousHandlerInvocation_QueueMaxNumber)),
								MessageHandlerThreadFactory)))
				.addFeature(new Feature.AsynchronousMessageDispatch()
						.setNumberOfMessageDispatchers(Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_AsynchronousMessageDispatch_NumberOfMessageDispatchers))
						.setDispatcherThreadFactory(MessageDispatchThreadFactory)
						.setMessageQueue(
								new LinkedBlockingQueue<IMessagePublication>(Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_MessageQueue))))
				.addPublicationErrorHandler(transactionBusIPublicationErrorHandler)
				.setProperty(IBusConfiguration.Properties.BusId, "transactionBusAsyncMethod"));
		bus.subscribe(transactionBusListener);
		return Optional.of(this);
	}
	
	public Optional<BusObserver<Transaction, BusTransactionTimeoutException>> pushAsync(Optional<Transaction> transaction) {
		IMessagePublication iMessagePublication = bus.publishAsync(transaction);
		return Optional.of(new TransactionBusObserver(transaction.get().transactionExecute(Optional.of(transactionExecuteAnalysis)),
													  transaction.get().transactionResult(Optional.of(transactionResultAnalysis))));
	}

	private static int minThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MinThreadCount);
	}

	private static int maxThreadCount(){
		return Integer.parseInt(Parameters_Module_Kernel_Bus_TransactionBus_MBassador_HandlerInvocation_MaxThreadCount);
	}
}
