package com.eric.server.components2;

import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理连接上下文和用户之间关系的工具
 * @author Eric
 *
 */
public class LocalChannelManager {

	//存储用户名与连接上下文对象的映射
	final private Map<String, ChannelHandlerContext> sessions = new ConcurrentHashMap<String, ChannelHandlerContext>();
	//存储连接上下文与用户名的映射
	final private Map<String,String> relations = new ConcurrentHashMap<String, String>();
	
	private static LocalChannelManager instance = new LocalChannelManager();
	
	public static LocalChannelManager getInstance(){
		return instance;
	}
	
	/**
	 * 增加用户与连接的上下文映射
	 * @param name
	 * @param ctx
	 */
	public void addContext(String name,ChannelHandlerContext ctx){
		synchronized (sessions) {
			sessions.put(name, ctx);
			relations.put(ctx.toString(), name);
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
	 * @param name
	 * @return
	 */
	public ChannelHandlerContext getContext(String name){
		return sessions.get(name);
	}
	
	/**
	 * 根据用户名删除session
	 * @param name
	 */
	public void removeContext(String name){
		sessions.remove(name);
	}
	
	/**
	 * 判断指定的用户名当前是否在线
	 * @param name
	 * @return
	 */
	public boolean isAvailable(String name){
		return sessions.containsKey(name)&&(sessions.get(name)!=null);
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
		String name = relations.get(ctx.toString());
		if(name != null){
			sessions.remove(name);
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
