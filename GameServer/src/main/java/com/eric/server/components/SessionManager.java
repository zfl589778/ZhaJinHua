package com.eric.server.components;

import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户-上下文
 * @author Eric
 *
 */
public class SessionManager {

	//存储用户名与连接上下文对象的映射
	final private Map<String, ChannelHandlerContext> sessions = new ConcurrentHashMap<String, ChannelHandlerContext>();
	//存储连接上下文与用户名的映射
	final private Map<String,String> relations = new ConcurrentHashMap<String, String>();
	
	private static SessionManager instance = new SessionManager();
	
	public static SessionManager getInstance(){
		return instance;
	}
	
	/**
	 * 增加用户与连接的上下文映射
	 * @param sessionId
	 * @param ctx
	 */
	public void addContext(String sessionId,ChannelHandlerContext ctx){
		synchronized (instance) {
			sessions.put(sessionId, ctx);
			relations.put(ctx.toString(), sessionId);
		}
	}
	
	/**
	 * 获取指定用户的连接上下文
	 * 
	 * @param name
	 * @return
	 */
	public ChannelHandlerContext getContext(ChannelHandlerContext ctx) {
		String name = relations.get(ctx.toString());
		if (name != null) {
			return sessions.get(name);
		}
		return null;
	}
	
	/**
	 * 获取指定用户的连接上下文
	 * @param sessionId
	 * @return
	 */
	public ChannelHandlerContext getContext(String sessionId){
		return sessions.get(sessionId);
	}
	
	/**
	 * 根据用户名删除session
	 * @param sessionId
	 */
	public void removeContext(String sessionId){
		sessions.remove(sessionId);
	}
	
	/**
	 * 判断指定的用户名当前是否在线
	 * @param sessionId
	 * @return
	 */
	public boolean isAvailable(String sessionId){
		return sessions.containsKey(sessionId)&&(sessions.get(sessionId)!=null);
	}
	
	/**
	 * 获取所有的用户名
	 * @return
	 */
	public synchronized Set<String> getAll(){
		return sessions.keySet();
	}
	
	/**
	 * 获取所有连接的上下文对象
	 * @return
	 */
	public synchronized Collection<ChannelHandlerContext> getAllClient(){
		return sessions.values();
	}
	
	/**
	 * 根据上下文删除用户session
	 * @param ctx
	 */
	public void removeContext(ChannelHandlerContext ctx){
		String sessionId = relations.get(ctx.toString());
		if(sessionId != null){
			sessions.remove(sessionId);
			relations.remove(ctx.toString());
		}
	}
	
	/**
	 * 统计在线人数
	 * @return
	 */
	public int staticClients(){
		return relations.size();
	}
}
