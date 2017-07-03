package com.eric.server.components2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eric.components.BeanFactoryUtil;

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
		logger.debug("[] channelUnregistered");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		logger.debug("[" + ctx.channel().remoteAddress().toString() + "] Connected");
		ctx.writeAndFlush(XML_SOCKET);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		try {
			ActionControllar control = BeanFactoryUtil.getBean(ActionControllar.class);
			String message = in.toString(Charset.forName("UTF-8"));
			logger.debug("[" + ctx.channel().remoteAddress() + "] Msg Received: " + message);
			try {
				message = control.MessageTranslate(message, ctx);
				if (message != null) {
					logger.debug("[" + ctx.channel().remoteAddress() + "] Msg Sent: " + message);
					ctx.writeAndFlush(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.error("Broken pipe was occured with ip: " + ctx.channel().remoteAddress().toString());
				logger.error(e.toString());
			}

		} catch (Exception e) {
			try {
				this.channelUnregistered(ctx);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		in.release();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info(cause.toString());
	}

}
