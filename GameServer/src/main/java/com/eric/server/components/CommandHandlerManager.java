package com.eric.server.components;

import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.eric.server.annotation.Command;

public class CommandHandlerManager {
	
	private Map<String, Object> handlers = new ConcurrentHashMap<String, Object>();
	
	private static CommandHandlerManager instance = new CommandHandlerManager();
	
	public static CommandHandlerManager getInstance(){
		return instance;
	}
	
	public void init(Map<String, Object> handlers){
		this.handlers = handlers;
	}
	
	public IServiceHandler getServiceHandlerByCommand(String command){
		IServiceHandler serviceHandler = null;
		for (Map.Entry<String,Object> obj : this.handlers.entrySet()) {
			Object handler = obj.getValue();
			Command c = handler.getClass().getAnnotation(Command.class);
			if(c!=null){
				if(command.equals(c.op())){
					serviceHandler = (IServiceHandler) handler;
					break;
				}
			}
		}
		return serviceHandler;
	}

}
