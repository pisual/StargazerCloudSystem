package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.resources.annotation.Parameters;

/** 
 *  @name queueParameters 核心参数列表
 *  @illustrate 系统所需的queueParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="queueParameters")
@Qualifier("queueParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="queueParameters")
@SuppressWarnings("unused")
public class QueueParameters {
	
	public QueueParameters() {}
	
	    //Event队列 Start
		/** @illustrate order Export 队列消费者数目 **/
		private static final String Kernel_Queue_TransactionExportEvent_Consumer_NumberOfConsumers = "1";
		/** @illustrate orderExport 队列缓存 **/
		private static final String Kernel_Queue_TransactionExportEventQueue_Memory_BufferSize = "65536";
		//Event队列 End
		
	    //TransmissionQueue队列 Start
		/** @illustrate 接收Event队列消费者数目 **/
		public static final String Kernel_Queue_TransmissionQueue_Consumer_NumberOfConsumers = "1";
		/** @illustrate 接收Event队列缓存 **/
		private static final String Kernel_Queue_TransmissionQueue_Memory_BufferSize = "65536";
		/** @illustrate 低速队列的寻轮时间 **/
		private static final String Kernel_Queue_TransmissionQueue_Strategy_SearchIntervalTime = "10000000000";
		//TransmissionQueue队列 End
		
		//Event队列 Start
		/** @illustrate 接收Event队列消费者数目 **/
		public static final String Kernel_Queue_ReceiveEventQueue_Consumer_NumberOfConsumers = "4";
		/** @illustrate 接收Event队列缓存 **/
		private static final String Kernel_Queue_ReceiveEventQueue_Memory_BufferSize = "65536";
		//Event队列 End
		
		//Event队列 Start
		/** @illustrate 接收Event队列消费者数目 **/
		public static final String Kernel_Queue_ReceiveEventBusQueue_Consumer_NumberOfConsumers = "4";
		/** @illustrate 接收Event队列缓存 **/
		private static final String Kernel_Queue_ReceiveEventBusQueue_Memory_BufferSize = "65536";
		//Event队列 End
		
		//系统核心日志队列配置 Start
		/** @illustrate 系统核心日志队列消费者数目 **/
		private static final String Kernel_Queue_ReceiveLogQueue_Consumer_NumberOfConsumers= "1";
		/** @illustrate 系统核心日志队列缓存 **/
		private static final String Kernel_Queue_ReceiveLogQueue_Memory_BufferSize = "65536";
		//系统核心日志队列配置 End
		
		//Kafka消息队列配置 Start
		/** @illustrate 消息推送的目的地址 **/
		private static final String Kernel_Queue_TransactionMessage_MessagePushQueueTopic = "127.0.0.1:";
		/** @illustrate 消息推送的目的Partition **/
		private static final String Kernel_Queue_TransactionMessage_MessagePushQueuePartition = "2";
		/** @illustrate 消息推送队列 **/
		private static final String Kernel_Queue_TransactionMessage_PushQueueTopic = "OrderPushMessageQueue";
		/** @illustrate 消息推送队列组 **/
		private static final String Kernel_Queue_TransactionMessage_PushQueueTopicGroup = "OrderPushMessageQueueGroup";
		/** @illustrate 消息接收队列 **/
		private static final String Kernel_Queue_TransactionMessage_AcquireQueueTopic = "OrderAcquireMessageQueue";
		/** @illustrate 消息接收队列组 **/
		private static final String Kernel_Queue_TransactionMessage_AcquireQueueTopicGroup = "OrderAcquireMessageQueueGroup";
		/** @illustrate 消息队列集群地址 **/
		private static final String Kernel_Queue_TransactionMessage_KafkaBinderBrokers = "127.0.0.1:9092";
		/** @illustrate 消息队列Zookeeper地址 **/
		private static final String Kernel_Queue_TransactionMessage_KafkaZookeeperBrokers = "127.0.0.1:9092";
		/** @illustrate ENABLE_AUTO_COMMIT_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_ENABLE_AUTO_COMMIT_CONFIG = "true";
		/** @illustrate AUTO_COMMIT_INTERVAL_MS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_AUTO_COMMIT_INTERVAL_MS_CONFIG = "1000";
		/** @illustrate SESSION_TIMEOUT_MS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_SESSION_TIMEOUT_MS_CONFIG = "30000";
		/** @illustrate MAX_POLL_RECORDS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_MAX_POLL_RECORDS_CONFIG = "100";
		/** @illustrate KEY_DESERIALIZER_CLASS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_KEY_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
		/** @illustrate VALUE_DESERIALIZER_CLASS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_VALUE_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.BytesDeserializer";
		/** @illustrate KEY_SERIALIZER_CLASS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_KEY_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";
		/** @illustrate VALUE_SERIALIZER_CLASS_CONFIG **/
		private static final String Kernel_Queue_TransactionMessage_VALUE_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.BytesSerializer";
		//Kafka消息队配置 End
}
