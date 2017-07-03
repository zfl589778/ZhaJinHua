package com.eric.server.service;

import io.netty.channel.ChannelHandlerContext;

import com.alibaba.fastjson.JSONObject;
import com.eric.server.annotation.Command;
import com.eric.server.components.IServiceHandler;
import com.eric.server.components.PushManager;

@Command(op="heartBeat")
public class HeartBeatHandler implements IServiceHandler{

	@Override
	public void execute(JSONObject paramObj,ChannelHandlerContext ctx) {
		JSONObject obj = new JSONObject();
		obj.put("rop", this.getClass().getAnnotation(Command.class).op());
		obj.put("check", "success");
		PushManager.pushSelf(JSONObject.toJSONString(obj), ctx);
	}

}
