package com.eric.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSONObject;
import com.eric.server.annotation.Command;
import com.eric.server.components.IServiceHandler;
import com.eric.server.components.SessionManager;

@Command(op="login")
public class LoginHandler implements IServiceHandler{

	private Logger logger = LoggerFactory.getLogger(LoginHandler.class);
	
	@Override
	public void execute(JSONObject paramObj,ChannelHandlerContext ctx) {
		String sessionId = paramObj.getString("sessionId");
		SessionManager.getInstance().addContext(sessionId, ctx);
		logger.debug("当前在线人数："+SessionManager.getInstance().staticClients());
	}

}
