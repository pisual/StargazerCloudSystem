package com.stargazerproject.spring.context.initialization.test;

import com.stargazerproject.analysis.impl.*;
import com.stargazerproject.analysis.resources.shell.*;
import com.stargazerproject.annotation.impl.AnnotationsImpl;
import com.stargazerproject.annotation.resources.AnnotationsScannerResourcesCharacteristic;
import com.stargazerproject.annotation.resources.shell.AnnotationsShell;
import com.stargazerproject.annotations.server.impl.AnnotationsServer;
import com.stargazerproject.annotations.server.listener.impl.AnnotationsServerListener;
import com.stargazerproject.annotations.server.manage.AnnotationsServerManage;
import com.stargazerproject.bus.impl.EventBus;
import com.stargazerproject.bus.impl.TransactionBus;
import com.stargazerproject.bus.resources.*;
import com.stargazerproject.bus.resources.shell.EventBusResourcesShell;
import com.stargazerproject.bus.resources.shell.TransactionBusResourcesShell;
import com.stargazerproject.bus.server.impl.EventBusServer;
import com.stargazerproject.bus.server.impl.TransactionBusServer;
import com.stargazerproject.bus.server.listener.impl.EventBusServerListener;
import com.stargazerproject.bus.server.listener.impl.TransactionBusServerListener;
import com.stargazerproject.bus.server.manage.EventBusServerManage;
import com.stargazerproject.bus.server.manage.TransactionBusServerManage;
import com.stargazerproject.cache.aop.configuration.ParametersInjectAOPConfiguration;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;
import com.stargazerproject.cache.datastructure.impl.*;
import com.stargazerproject.cache.impl.EventResultCache;
import com.stargazerproject.cache.impl.*;
import com.stargazerproject.cache.impl.resources.*;
import com.stargazerproject.cache.impl.resources.shell.*;
import com.stargazerproject.cache.server.impl.AggregateRootIndexCacheServer;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterCacheServer;
import com.stargazerproject.cache.server.impl.TransactionCacheServer;
import com.stargazerproject.cache.server.listener.impl.AggregateRootIndexCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.TransactionCacheServerListener;
import com.stargazerproject.cache.server.manage.AggregateRootIndexCacheServerManage;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.cache.server.manage.TransactionCacheServerManage;
import com.stargazerproject.cell.aop.configuration.HystrixConfigurationS;
import com.stargazerproject.cell.method.sequence.InitializationCellsGroupModel;
import com.stargazerproject.cell.method.sequence.Test_GetCellsGroupIDModel;
import com.stargazerproject.cell.method.sequence.Test_NowTimeModel;
import com.stargazerproject.consumer.impl.EventBusConsumer;
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.inject.impl.InjectImpl;
import com.stargazerproject.inject.resources.InjectClassMethodCharacteristic;
import com.stargazerproject.inject.resources.InjectSearchMethodCharacteristic;
import com.stargazerproject.inject.resources.shell.InjectShell;
import com.stargazerproject.inject.server.impl.InjectServer;
import com.stargazerproject.inject.server.listener.impl.InjectServerListener;
import com.stargazerproject.inject.server.manage.InjectServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.messagequeue.impl.TransactionMessageQueue;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueAcquireCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueCallBackCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueControlCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueuePushCharacteristic;
import com.stargazerproject.messagequeue.resources.shell.TransactionMessageQueueShall;
import com.stargazerproject.messagequeue.server.impl.TransactionMessageQueueServer;
import com.stargazerproject.messagequeue.server.listener.impl.TransactionMessageQueueServerListener;
import com.stargazerproject.messagequeue.server.manage.TransactionMessageQueueServerManage;
import com.stargazerproject.negotiate.impl.NodenNegotiateImpl;
import com.stargazerproject.negotiate.impl.ZoneNegotiateImpl;
import com.stargazerproject.negotiate.resources.*;
import com.stargazerproject.negotiate.resources.impl.NegotiateInjectParameterMonitorListenerCharacteristic;
import com.stargazerproject.negotiate.resources.impl.NegotiateParametersInjectInitializationListenerCharacteristic;
import com.stargazerproject.negotiate.resources.shell.NodenNegotiateShell;
import com.stargazerproject.negotiate.server.impl.NodeNegotiateServer;
import com.stargazerproject.negotiate.server.listener.impl.NodeNegotiateListener;
import com.stargazerproject.negotiate.server.manage.NodeNegotiateServerManage;
import com.stargazerproject.queue.impl.EventBusQueue;
import com.stargazerproject.queue.impl.EventQueue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.queue.impl.TransactionExportQueue;
import com.stargazerproject.queue.resources.impl.*;
import com.stargazerproject.queue.resources.shell.EventBusDisruptorShell;
import com.stargazerproject.queue.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.shell.TransactionExportEventDisruptorShell;
import com.stargazerproject.queue.server.impl.EventBusQueueServer;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.impl.TransactionExportEventQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventBusQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.TransactionExportEventQueueServerListener;
import com.stargazerproject.queue.server.manage.EventBusQueueServerManage;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.queue.server.manage.TransactionExportEventQueueServerManage;
import com.stargazerproject.resources.parameter.*;
import com.stargazerproject.resources.service.SystemServiceParameterList;
import com.stargazerproject.sequence.impl.StandardSequenceImpl;
import com.stargazerproject.sequence.resources.SequenceTransactionCharacteristic;
import com.stargazerproject.sequence.resources.shell.SequenceResourcesShell;
import com.stargazerproject.sequence.server.impl.StandardSequenceServer;
import com.stargazerproject.sequence.server.listener.impl.StandardServerListener;
import com.stargazerproject.sequence.server.manage.StandardServerManage;
import com.stargazerproject.serializable.server.impl.SerializableServer;
import com.stargazerproject.serializable.server.listener.impl.SerializableServerListener;
import com.stargazerproject.serializable.server.manage.SerializableServerManage;
import com.stargazerproject.service.aop.configuration.ServerDependDetectionAOPConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.service.resources.ServiceControlCharacteristic;
import com.stargazerproject.service.resources.ServiceInitializationCharacteristic;
import com.stargazerproject.service.resources.shell.ServerShell;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.transaction.impl.StandardEvent;
import com.stargazerproject.transaction.impl.StandardEventResult;
import com.stargazerproject.transaction.impl.StandardTransaction;
import com.stargazerproject.transaction.impl.resources.shell.BaseEventResultShell;
import com.stargazerproject.transaction.impl.resources.shell.BaseEventShell;
import com.stargazerproject.transaction.impl.resources.shell.BaseTransactionResultShell;
import com.stargazerproject.transaction.impl.resources.shell.BaseTransactionShell;
import com.stargazerproject.userinterface.impl.UserInterfaceImpl;
import com.stargazerproject.userinterface.resources.*;
import com.stargazerproject.userinterface.resources.shall.FrameShell;
import com.stargazerproject.userinterface.resources.shall.LoadingFrameShell;
import com.stargazerproject.userinterface.resources.shall.MainFrameShell;
import com.stargazerproject.userinterface.server.impl.FrameUserInterfaceServer;
import com.stargazerproject.userinterface.server.listener.impl.FrameUserInterfaceListener;
import com.stargazerproject.userinterface.server.manage.FrameUserInterfaceServerManage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="Spring.properties")
public class GlobalAnnotationApplicationContextInitialization {

	public static void ApplicationContextInitialize(String args[]){
		System.setProperty("java.awt.headless", "false");
		setProfiles();
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
	    args,

		/**Depend SystemParameter **/
		SystemParameterCahce.class,
		SystemParameterCahceCharacteristic.class,
		SystemParameterCahceShell.class,
		SystemParameterCacheServer.class,
		SystemParameterCacheServerListener.class,
		SystemParameterCacheServerManage.class,
		NegotiateParameters.class,
		SystemParameters.class,
		SequenceParameters.class,
		InformationParameter.class,
		ParametersInjectAOPConfiguration.class,
		TransactionParameters.class,
		BusParameters.class,

		/**Depend ObjectParameterCache **/
		ObjectParameterCache.class,

		/**Depend AggregateRootCache **/
		AggregateRootCache.class,

		/**Depend SocketChannelCache **/
		SocketChannelCache.class,

		/**Depend InterProcessSemaphoreMutexCache **/
		InterProcessSemaphoreMutexCache.class,

		/**Depend LeaderLatchParameterCache **/
		LeaderLatchParameterCache.class,

		/**Depend TreeCacheCache **/
		TreeCacheCache.class,

		/**Depend ServerCache **/
		ServerCache.class,

		/**Depend ServerListCache **/
		ServerListCache.class,

		/**Depend EventResultCache **/
		EventResultCache.class,

		/**Depend EventInteractionCache **/
		EventInteractionCache.class,

		/**Depend EventResultCache **/
		ResultCacheMultimapCacheShell.class,
		EventResultCache.class,

		/**Depend TransactionInteractionCache **/
		TransactionInteractionCache.class,

		/**Depend TransactionResultCache **/
		TransactionResultCache.class,

		/**Depend BaseDataStructureCache **/
		BaseDataStructureCache.class,

		/**Depend OrderQueueMessage**/
		TransactionMessageQueue.class,
		TransactionMessageQueueServer.class,
		TransactionMessageQueueServerListener.class,
		TransactionMessageQueueServerManage.class,
		TransactionMessageQueueAcquireCharacteristic.class,
		TransactionMessageQueueControlCharacteristic.class,
		TransactionMessageQueuePushCharacteristic.class,
		TransactionMessageQueueShall.class,
		TransactionMessageQueueCallBackCharacteristic.class,

		/**Depend Service*/
		ServiceInitializationCharacteristic.class,
		ServiceControlCharacteristic.class,
		ServerShell.class,
		ServerDependDetectionAOPConfiguration.class,

		/**Depend nodenNegotiate*/
		NodenNegotiateImpl.class,
		ZoneNegotiateImpl.class,
		NegotiateConnectionStateListenerCharacteristic.class,
		NegotiateControlCharacteristic.class,
		NegotiateCuratorFrameworkCharacteristic.class,
		NegotiateLeaderLeaderLatchListenerCharacteristic.class,
		NegotiateLeaderMethodCharacteristic.class,
		NegotiateNodeCuratorListenerCharacteristic.class,
		NegotiateNodeMethodCharacteristic.class,
		NegotiateRegisteredWatcherCharacteristic.class,
		NegotiateRetryPolicyCharacteristic.class,
		NodenNegotiateShell.class,
		NodeNegotiateServer.class,
		NodeNegotiateListener.class,
		NodeNegotiateServerManage.class,
		NegotiateInjectParameterMonitorListenerCharacteristic.class,
		NegotiateParametersInjectInitializationListenerCharacteristic.class,

		/**Depend BigCacheIndexCahce**/
		BigCacheIndexCahce.class,

		/**Depend ByteArrayCache**/
		ByteArrayCache.class,
		ByteArrayCacheCacheConfigurationCharacteristic.class,
		ByteArrayCacheCacheManagerCharacteristic.class,
		ByteArrayCacheConfigurationCharacteristic.class,
		ByteArrayCacheShell.class,
		ByteArrayCacheServer.class,
		ByteArrayCacheServerListener.class,
		ByteArrayCacheServerManage.class,


		/**Depend ByteArrayCache**/
		AggregateRootIndexCache.class,
		AggregateRootIndexCacheShell.class,
		AggregateRootIndexCacheCacheLoaderCharacteristic.class,
		AggregateRootIndexCacheLoadingCacheCharacteristic.class,
		AggregateRootIndexCacheRemovalListenerCharacteristic.class,
		AggregateRootIndexCacheServer.class,
		AggregateRootIndexCacheServerListener.class,
		AggregateRootIndexCacheServerManage.class,

		/**Depend EventQueue**/
		EventQueue.class,
		EventDisruptorShell.class,
		EventFactory.class,
		EventHandler.class,
		EventQueueThreadFactory.class,
		EventQueueServer.class,
		EventQueueServerListener.class,
		EventQueueServerManage.class,
		EventConsumer.class,
		EventResultMergeHandler.class,
		CleanEventHandler.class,

		/**Depend EventBusQueue**/
		EventBusQueue.class,
		EventBusDisruptorShell.class,
		EventBusQueueServer.class,
		EventBusQueueServerListener.class,
		EventBusQueueServerManage.class,
		EventBusConsumer.class,
		EventBusHandler.class,

		/**Depend LogCache**/
		LogQueue.class,
		LogDisruptorShell.class,
		LogEventFactory.class,
		LogHandler.class,
		LogQueueThreadFactory.class,
		LogQueueServer.class,
		LogQueueServerListener.class,
		LogQueueServerManage.class,
		CleanLogHandler.class,

		/**Depend LogCache**/
		TransactionExportQueue.class,
		TransactionExportEventDisruptorShell.class,
		TransactionExportEventFactory.class,
		TransactionExportEventHandler.class,
		TransactionExportEventThreadFactory.class,
		TransactionExportEventQueueServer.class,
		TransactionExportEventQueueServerListener.class,
		TransactionExportEventQueueServerManage.class,
		CleanTransactionExportEventHandler.class,

		/**Depend TransactionCache**/
		TransactionCache.class,
		TransactionCacheCacheLoaderCharacteristic.class,
		TransactionCacheLoadingCacheCharacteristic.class,
		TransactionCacheRemovalListenerCharacteristic.class,
		TransactionCahceShell.class,
		TransactionCacheServer.class,
		TransactionCacheServerListener.class,
		TransactionCacheServerManage.class,

		/**Depend Resources**/
		CacheParameters.class,
		QueueParameters.class,
		UIParameters.class,
		SystemServiceParameterList.class,
		InformationParameter.class,
		InjectParameters.class,
		NegotiateParameters.class,
		QueueParameters.class,
		SequenceParameters.class,
		SystemParameters.class,

		/**Depend Log**/
		GroupLogConfiguration.class,

		/**Depend Service**/
		GroupServiceConfiguration.class,

		/**User Interface Service**/
		MainFrameBackgroundJlabelCharacteristic.class,
		MainFrameConsoleTextPaneCharacteristic.class,
		MainFrameJFrameCharacteristic.class,
		MainFrameJScrollPaneCharacteristic.class,
		MainFrameLayoutCharacteristic.class,
		MainFrameLogoJlabelCharacteristic.class,
		MainFrameRightConsoleTextPaneCharacteristic.class,
		MainFrameRightJScrollPaneCharacteristic.class,
		MainFrameStructureTopologyJlabelCharacteristic.class,
		MainFrameShell.class,
		MainFrameLogoClickListenerCharacteristic.class,
		MainFrameMouseAdapterListenerCharacteristic.class,
		MainFrameMouseMotionAdapterListenerCharacteristic.class,
		MainFramePointCharacteristic.class,
		LoadingBaseFrameJDialogCharacteristic.class,
		LoadingJProgressBarCharacteristic.class,
		LoadingProgressInfoCharacteristic.class,
		LoadingFrameBackgroundJlabelCharacteristic.class,
		LoadingFrameLayoutCharacteristic.class,
		LoadingFrameShell.class,
		UserInterfaceImpl.class,
		FrameUserInterfaceServer.class,
		FrameUserInterfaceListener.class,
		FrameUserInterfaceServerManage.class,
		LoadingJProgressBarUI.class,
		FrameShell.class,

		/**Depend Sequence */
		StandardSequenceImpl.class,
		SequenceResourcesShell.class,
		StandardSequenceServer.class,
		StandardServerListener.class,
		StandardServerManage.class,
		SequenceTransactionCharacteristic.class,

		/**Depend AnnotationImpl*/
		AnnotationsImpl.class,
		AnnotationsScannerResourcesCharacteristic.class,
		AnnotationsShell.class,
		AnnotationsServer.class,
		AnnotationsServerListener.class,
		AnnotationsServerManage.class,

		/**Depend Bus**/
		EventBus.class,
		EventBusResourcesShell.class,
		EventBusResourcesShell.class,
		EventBusAsyncMethodMBassadorCharacteristic.class,
		EventBusBlockMethodMBassadorCharacteristic.class,
		EventBusListener.class,
		EventBusServer.class,
		EventBusServerListener.class,
		EventBusServerManage.class,


		/**Depend EventBusQueue**/
		TransactionBus.class,
		TransactionBusResourcesShell.class,
		TransactionBusAsyncMethodMBassadorCharacteristic.class,
		TransactionBusBlockMethodMBassadorCharacteristic.class,
		TransactionBusListener.class,
		TransactionBusServer.class,
		TransactionBusServerListener.class,
		TransactionBusServerManage.class,

//
//		/**Depend Transmission Queue**/
//		TransmissionConsumer.class,
//		TransmissionQueue.class,
//		CleanTransmissionEventHandler.class,
//		TransmissionEventFactory.class,
//		TransmissionQueueHandler.class,
//		TransmissionQueueThreadFactory.class,
//		TransmissionDisruptorShell.class,
//		TransmissionQueueServer.class,
//		TransmissionQueueServerListener.class,
//		TransmissionQueueServerManage.class,
		
		/**Depend Inject**/
		InjectImpl.class,
		InjectClassMethodCharacteristic.class,
		InjectSearchMethodCharacteristic.class,
		InjectShell.class,
		InjectServer.class,
		InjectServerListener.class,
		InjectServerManage.class,

		/**Depend Analysis**/
		EventResultAnalysisImpl.class,
        EventResultAnalysisShell.class,
        EventAssembleAnalysisImpl.class,
        EventAssembleAnalysisShell.class,
        EventExecuteAnalysisImpl.class,
        EventExecuteAnalysisShell.class,
        LogAnalysisImpl.class,
		TransactionAssembleAnalysisImpl.class,
		TransactionExecuteAnalysisImpl.class,
		TransactionResultAnalysisImpl.class,
		TransactionAssembleAnalysisShell.class,
		TransactionExecuteAnalysisShell.class,
		TransactionResultAnalysisShell.class,



//		/**Depend CellsInformation**/
//		CellsInformation.class,
//		CellsInformationByteToMessageDecoderHandlerCharacteristic.class,
//		CellsInformationClientRegisterHandlerCharacteristic.class,
//		CellsInformationControlCharacteristic.class,
//		CellsInformationEventHandlerCharacteristic.class,
//		CellsInformationHandlerGuide.class,
//		CellsInformationHeartbeatHandlerCharacteristic.class,
//		CellsInformationMessageToByteEncoderHandlerCharacteristic.class,
//		CellsInformationMethodCharacteristic.class,
//		CellsInformationsOutScourHandler.class,
//		CellsInformationShell.class,
//		CellsInformationServer.class,
//		CellsInformationServerListener.class,
//		CellsInformationServerManage.class,
		
		/**Depend Serializables**/
		SerializableServer.class,
		SerializableServerListener.class,
		SerializableServerManage.class,

		/**Depend Analysis**/
		EventAssembleAnalysisImpl.class,
		EventExecuteAnalysisImpl.class,
		EventAssembleAnalysisShell.class,
		EventExecuteAnalysisShell.class,

		/**Depend Transaction**/
		StandardEvent.class,
		StandardEventResult.class,
		StandardTransaction.class,
		BaseEventResultShell.class,
		BaseEventShell.class,
		BaseTransactionShell.class,
		BaseTransactionResultShell.class,

        /**Depend HystrixConfigurationS**/
        HystrixConfigurationS.class,

		/**测试用方法，实际需要动态注入**/
		InitializationCellsGroupModel.class,
		Test_GetCellsGroupIDModel.class,
        Test_NowTimeModel.class

		);
	} 
	
	private static void setProfiles(){
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
	}
	
}
