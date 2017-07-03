package com.eric.server.main;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eric.server.annotation.Command;
import com.eric.server.components.CommandHandlerManager;
import com.eric.server.components.ConnectionHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

public class StartServer {
	
	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

	private static void runNetty(int port) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(2);
		EventLoopGroup workerGroup = new NioEventLoopGroup(4);
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
							ch.pipeline().addLast(new ConnectionHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			// Start the client
			ChannelFuture future = server.bind(port).sync();
			System.out.println("Game Server start");
			// Wait until the connection is closed
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	private static void initHandler() {
		System.out.println("======================初始化handler start==================================");
		Map<String, Object> commands = ctx.getBeansWithAnnotation(Command.class);
		for (Map.Entry<String,Object> obj : commands.entrySet()) {
			Object handler = obj.getValue();
			Command c = handler.getClass().getAnnotation(Command.class);
			if(c!=null){
				System.out.println(c.op()+":"+handler.getClass().getSimpleName());
			}
		}
		CommandHandlerManager.getInstance().init(commands);
		System.out.println("======================初始化handler end====================================");
	}

	public static void main(String[] args) throws InterruptedException {
		//初始化操作处理器
		initHandler();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runNetty(8888);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
