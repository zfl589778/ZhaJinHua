package com.eric.server.components;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ConnectionHandler extends ChannelHandlerAdapter {

	private static final String XML_SOCKET = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
	private Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		//TODO 退出事件
		logger.debug("[" + ctx.channel().remoteAddress().toString() + "] channelUnregistered");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		logger.debug("[" + ctx.channel().remoteAddress().toString() + "] Connected");
		ctx.writeAndFlush(XML_SOCKET);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		try{
			String message = in.toString(Charset.forName("UTF-8"));
			logger.debug("Msg from [" + ctx.channel().remoteAddress() + "] Received: " + message);
			JSONObject paramObj = JSONObject.parseObject(message);
			String op = paramObj.getString("op");
			if(StringUtils.isBlank(op)){
				throw new RuntimeException("未定义该指令类型");
			}
			CommandHandlerManager.getInstance().getServiceHandlerByCommand(op).execute(paramObj,ctx);
		}catch(Exception e){
			try {
				this.channelUnregistered(ctx);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			in.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info(cause.toString());
	}

}
