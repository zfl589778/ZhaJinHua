package com.eric.server.components;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class PushManager {

	private static Logger logger = LoggerFactory.getLogger(PushManager.class);
	
	/**
	 * 推送
	 * @param sessionId
	 * @param msg
	 */
	public synchronized static void push(String sessionId,String msg){
		ChannelHandlerContext context = SessionManager.getInstance().getContext(sessionId);
		logger.debug("Msg Sent to [" + context.channel().remoteAddress() + "]:" + msg);
		context.writeAndFlush(msg);
	}
	
	/**
	 * 推送给自己
	 * @param msg
	 * @param target
	 */
	public synchronized static void pushSelf(String msg,ChannelHandlerContext ctx){
		logger.debug("Msg Sent to [" + ctx.channel().remoteAddress() + "]:" + msg);
		ctx.writeAndFlush(msg);
	}
	
	/**
	 * 推送给房间
	 * @param roomNumber
	 * @param msg
	 */
	public synchronized static void pushRoom(Integer roomNumber,String msg){
		List<ChannelHandlerContext> ctxList = RoomMemberManager.getInstance().getContextList(roomNumber);
		for (ChannelHandlerContext ctx : ctxList) {
			logger.debug("Msg Sent to [" + ctx.channel().remoteAddress() + "]:" + msg);
			ctx.writeAndFlush(msg);
		}
	}
	
	/**
	 * 全服推送
	 * @param msg
	 */
	public synchronized static void pushAll(String msg){
		Collection<ChannelHandlerContext> ctxList = SessionManager.getInstance().getAllClient();
		for (ChannelHandlerContext ctx : ctxList) {
			logger.debug("Msg Sent to [" + ctx.channel().remoteAddress() + "]:" + msg);
			ctx.writeAndFlush(msg);
		}
	}
}
