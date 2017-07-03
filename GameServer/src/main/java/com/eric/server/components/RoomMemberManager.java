package com.eric.server.components;

import io.netty.channel.ChannelHandlerContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class RoomMemberManager {

	private Map<Integer, List<ChannelHandlerContext>> rooms = new ConcurrentHashMap<Integer, List<ChannelHandlerContext>>();
	
	private static RoomMemberManager instance = new RoomMemberManager();
	
	public static RoomMemberManager getInstance(){
		return instance;
	}
	
	public void addContext(Integer roomNumber,ChannelHandlerContext ctx){
		synchronized (instance) {
			List<ChannelHandlerContext> members = rooms.get(roomNumber);
			if(members == null){
				members = new LinkedList<ChannelHandlerContext>();
				members.add(ctx);
				rooms.put(roomNumber, members);
				return;
			}
			if(members.size()>=6){
				return;
			}
			members.add(ctx);
		}
	}
	
	public List<ChannelHandlerContext> getContextList(Integer roomNumber){
		List<ChannelHandlerContext> members = rooms.get(roomNumber);
		if(members == null){
			members = new LinkedList<ChannelHandlerContext>();
		}
		return members;
	}
}
