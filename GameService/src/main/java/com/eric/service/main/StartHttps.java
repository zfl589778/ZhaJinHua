package com.eric.service.main;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.ServerConnector;

public class StartHttps {

	private static final String resourceBase = "./webapp";
	public static void main(String[] args) {
    	String webXmlPath = StartHttps.class.getResource("/bus/web.xml").getPath();
    	String xmlConfigPath = StartHttps.class.getResource("/bus/jetty.xml").getPath();
    	String contextPath = MainConfig.getContextPath();
    	startJetty(null, xmlConfigPath, contextPath, resourceBase, webXmlPath);
	}
	
	//启动单个工程目录
    public static void startJetty(String[] prjs, String xmlConfigPath, String contextPath, String resourceBase, String webXmlPath){
    try{
    	
    	JettyCustomServer server = new JettyCustomServer();
		HttpConfiguration http_config = new HttpConfiguration();
		HttpConfiguration https_config = new HttpConfiguration(http_config);
		
		https_config.addCustomizer(new SecureRequestCustomizer());
		https_config.setSecureScheme("https");
		ServerConnector httpConnector = new ServerConnector(server);
		httpConnector.addConnectionFactory(new HttpConnectionFactory(http_config));
		httpConnector.setPort(MainConfig.getPort());
		httpConnector.setReuseAddress(true);
		httpConnector.setIdleTimeout(15000);

		//setup the constraint that causes all http requests to return a !403 error
		ConstraintSecurityHandler security = new ConstraintSecurityHandler();        
		
		server.initServerHttps(security, xmlConfigPath, contextPath, resourceBase, webXmlPath, null, null);

		server.setHandler(security);
		server.setConnectors(new Connector[] { httpConnector });
    	
    	server.setStopAtShutdown(true);
    	
    	server.setAttribute("CharacterEncoding", "UTF-8");
    	server.start();
    	
    	System.out.println("Jetty 服务器启动 >> ===============================================");
    	System.out.println("		Jetty jetty配置路径 >> ========" + xmlConfigPath);
		System.out.println("		Jetty 上下文路径（类路径） >> ========" + server.getContextPath());
		System.out.println("		Jetty 资源根路径 >> ========" + server.getResourceBase());
		System.out.println("		Jetty web路径 >> ========" + webXmlPath);
		System.out.println("		Jetty 当前线程数(最小线程数) >> ======== " + server.getThreadPool().getThreads());
		System.out.println("		Jetty 空闲线程数 >> ======== " + server.getThreadPool().getIdleThreads());
		System.out.println("Jetty 服务器完成 >> ===============================================");
		
		server.join();
		
    }catch(Exception e){e.printStackTrace();}
    }
   
}
