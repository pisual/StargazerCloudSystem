package com.stargazerproject.spring.context.initialization.test;

import com.stargazer.segmentation.impl.EventSegmentation;
import com.stargazerproject.analysis.impl.*;
import com.stargazerproject.analysis.resources.shell.EventAssembleAnalysisShell;
import com.stargazerproject.analysis.resources.shell.EventExecuteAnalysisShell;
import com.stargazerproject.analysis.resources.shell.EventResultAnalysisShell;
import com.stargazerproject.annotation.impl.AnnotationsImpl;
import com.stargazerproject.annotation.resources.AnnotationsScannerResourcesCharacteristic;
import com.stargazerproject.annotation.resources.shell.AnnotationsShell;
import com.stargazerproject.annotations.server.impl.AnnotationsServer;
import com.stargazerproject.annotations.server.listener.impl.AnnotationsServerListener;
import com.stargazerproject.annotations.server.manage.AnnotationsServerManage;
import com.stargazerproject.bus.impl.EventBus;
import com.stargazerproject.bus.impl.EventBusObserver;
import com.stargazerproject.bus.resources.EventBusBlockMethodCharacteristic;
import com.stargazerproject.bus.resources.EventBusNoBlockMethodCharacteristic;
import com.stargazerproject.bus.resources.shell.EventBusObserverShell;
import com.stargazerproject.bus.resources.shell.EventBusResourcesShell;
import com.stargazerproject.bus.server.impl.EventBusServer;
import com.stargazerproject.bus.server.listener.impl.EventBusServerListener;
import com.stargazerproject.bus.server.manage.EventBusServerManage;
import com.stargazerproject.cache.aop.configuration.ParametersInjectAOPConfiguration;
import com.stargazerproject.cache.datastructure.impl.*;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.TransactionCache;
import com.stargazerproject.cache.impl.resources.*;
import com.stargazerproject.cache.impl.resources.shell.ByteArrayCacheShell;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.impl.resources.shell.TransactionCahceShell;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterCacheServer;
import com.stargazerproject.cache.server.impl.TransactionCacheServer;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.TransactionCacheServerListener;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.cache.server.manage.TransactionCacheServerManage;
import com.stargazerproject.consumer.impl.EventBusConsumer;
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.consumer.impl.EventExecuteConsumer;
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
import com.stargazerproject.sequence.resources.ParallelSequenceTransactionCharacteristic;
import com.stargazerproject.sequence.resources.SequenceTransactionCharacteristic;
import com.stargazerproject.sequence.resources.shell.SequenceResourcesShell;
import com.stargazerproject.sequence.server.impl.StandardSequenceServer;
import com.stargazerproject.sequence.server.listener.impl.StandardServerListener;
import com.stargazerproject.sequence.server.manage.StandardServerManage;
import com.stargazerproject.serializable.impl.NetworkTransmissionSerializables;
import com.stargazerproject.serializable.server.impl.SerializableServer;
import com.stargazerproject.serializable.server.listener.impl.SerializableServerListener;
import com.stargazerproject.serializable.server.manage.SerializableServerManage;
import com.stargazerproject.serializable.shell.NetworkTransmissionSerializablesShell;
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
import com.stargazerproject.transaction.impl.resources.shell.BaseTransactionShell;
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

		/**Depend ObjectParameterCache **/
		ObjectParameterCache.class,
		
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
		
		EventSegmentation.class,
		
//		/**User Interface Service**/
//		MainFrameBackgroundJlabelCharacteristic.class,
//		MainFrameConsoleTextPaneCharacteristic.class,
//		MainFrameJFrameCharacteristic.class,
//		MainFrameJScrollPaneCharacteristic.class,
//		MainFrameLayoutCharacteristic.class,
//		MainFrameLogoJlabelCharacteristic.class,
//		MainFrameRightConsoleTextPaneCharacteristic.class,
//		MainFrameRightJScrollPaneCharacteristic.class,
//		MainFrameStructureTopologyJlabelCharacteristic.class,
//		MainFrameShell.class,
//		MainFrameLogoClickListenerCharacteristic.class,
//		MainFrameMouseAdapterListenerCharacteristic.class,
//		MainFrameMouseMotionAdapterListenerCharacteristic.class,
//		MainFramePointCharacteristic.class,
//		LoadingBaseFrameJDialogCharacteristic.class,
//		LoadingJProgressBarCharacteristic.class,
//		LoadingProgressInfoCharacteristic.class,
//		LoadingFrameBackgroundJlabelCharacteristic.class,
//		LoadingFrameLayoutCharacteristic.class,
//		LoadingFrameShell.class,
//		UserInterfaceImpl.class,
//		FrameUserInterfaceServer.class,
//		FrameUserInterfaceListener.class,
//		FrameUserInterfaceServerManage.class,
//		LoadingJProgressBarUI.class,
//		FrameShell.class,
		
		/**Depend Sequence*/
		StandardSequenceImpl.class,
		SequenceResourcesShell.class,
		ParallelSequenceTransactionCharacteristic.class,
		SequenceTransactionCharacteristic.class,
		StandardSequenceServer.class,
		StandardServerListener.class,
		StandardServerManage.class,
		
		/**Depend AnnotationImpl*/
		AnnotationsImpl.class,
		AnnotationsScannerResourcesCharacteristic.class,
		AnnotationsShell.class,
		AnnotationsServer.class,
		AnnotationsServerListener.class,
		AnnotationsServerManage.class,
		
		/**Depend Bus**/
		EventBus.class,
		EventBusObserver.class,
		EventBusObserverShell.class,
		EventBusResourcesShell.class,
		EventBusBlockMethodCharacteristic.class,
		EventBusNoBlockMethodCharacteristic.class,
		EventBusServer.class,
		EventBusServerListener.class,
		EventBusServerManage.class,
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

		EventExecuteConsumer.class,
		
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
		NetworkTransmissionSerializables.class,
		NetworkTransmissionSerializablesShell.class,
		SerializableServer.class,
		SerializableServerListener.class,
		SerializableServerManage.class,

		/**Depend Analysis**/
		EventAssembleAnalysisImpl.class,
		EventExecuteAnalysisImpl.class,
		EventAssembleAnalysisShell.class,
		EventExecuteAnalysisShell.class,
		SequenceTransactionResultAnalysisImpl.class,

		/**Depend Transaction**/
		StandardEvent.class,
		StandardEventResult.class,
		StandardTransaction.class,
		BaseEventResultShell.class,
		BaseEventShell.class,
		BaseTransactionShell.class

		);
	} 
	
	private static void setProfiles(){
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
	}
	
}
