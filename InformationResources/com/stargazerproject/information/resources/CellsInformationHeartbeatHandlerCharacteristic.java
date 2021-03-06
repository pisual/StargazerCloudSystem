package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Response;
import com.stargazerproject.information.model.ResponseType;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@Component(value="cellsInformationHeartbeatHandlerCharacteristic")
@Qualifier("cellsInformationHeartbeatHandlerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Sharable
public class CellsInformationHeartbeatHandlerCharacteristic extends SimpleChannelInboundHandler<Object> implements BaseCharacteristic<SimpleChannelInboundHandler<Object>>{
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public CellsInformationHeartbeatHandlerCharacteristic() {}
	
	@Override
	public Optional<SimpleChannelInboundHandler<Object>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
        if (object instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) object;
            
            if (event.state() == IdleState.READER_IDLE) {
            	Response response = new Response(Optional.of(ResponseType.PING));
            	channelHandlerContext.write(response);
			channelHandlerContext.fireChannelReadComplete();
            	log.INFO(this, "Cells Information : No data is read in the specified time, and the heartbeat data will be tried to keep the connection : 在指定时间内没有读取数据,将尝试发送心跳数据保持连接");
            } 
            
            else if (event.state() == IdleState.WRITER_IDLE) {
            	Response response = new Response(Optional.of(ResponseType.PING));
            	channelHandlerContext.write(response);
			channelHandlerContext.fireChannelReadComplete();
            	log.INFO(this, "Cells Information : No data is written in the specified time, and the heartbeat data is attempted to keep the connection : 在指定时间内没有写入数据,将尝试发送心跳数据保持连接");
            } 
            
            else if (event.state() == IdleState.ALL_IDLE) {
            	channelHandlerContext.disconnect();
            	log.ERROR(this, "Cells Information : Connection timeout, heartbeat connection interrupt, will close this connection channel :连接超时，心跳连接中断，将关闭此连接通道");
            }
        }
    }
	
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
		if(object instanceof Response){
			Response response = (Response) object;
			switch (response.responseType().get()) {
			case PING:
				log.INFO(object, "CellsInformation : Received PING request，response REPING : 收到Ping请求，即将回应REPING");
				Response responseReplays = new Response(Optional.of(ResponseType.REPING));
				channelHandlerContext.write(responseReplays);
				channelHandlerContext.fireChannelReadComplete();
				break;
			case REPING:
				log.INFO(object, "CellsInformation : Receive a response : 收到回应");
				channelHandlerContext.fireChannelReadComplete();
				break;
			default:
				channelHandlerContext.fireChannelRead(object);
				break;
				}
			}
		
		else{
			channelHandlerContext.fireChannelRead(object);
		}
		
		}
    
}
