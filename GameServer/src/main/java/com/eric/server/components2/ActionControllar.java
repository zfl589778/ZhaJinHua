package com.eric.server.components2;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class ActionControllar {

	private Logger logger = LoggerFactory.getLogger(ActionControllar.class);

	private StringWriter errors = new StringWriter();
	
	public ActionControllar() {
	}

	public String MessageTranslate(String message, ChannelHandlerContext ctx) {
		try {
			JSONMessageHandler handler = new JSONMessageHandler();
			Message msg = handler.deserializer(message);
			String responseMsg = handler.serializer(msg);
			return responseMsg;
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(errors));
			logger.error("Invalid Message: " + message);
			return "";
		}
	}


}
