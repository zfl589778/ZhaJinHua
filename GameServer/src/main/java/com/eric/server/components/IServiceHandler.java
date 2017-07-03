package com.eric.server.components;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSONObject;

public interface IServiceHandler {

	void execute(JSONObject paramObj,ChannelHandlerContext ctx);
	
}
