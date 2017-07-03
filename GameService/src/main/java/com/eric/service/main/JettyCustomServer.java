package com.eric.service.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.xml.sax.SAXException;

public class JettyCustomServer extends Server {

	private String xmlConfigPath;

	private String contextPath;

	private String warPath;

	private String resourceBase = "./webRoot";

	private String webXmlPath = "./webRoot/WEB-INF/web.xml";
	private Map<Class<?>, WebServlet> servletMap = new HashMap<Class<?>, WebServlet>();
	public JettyCustomServer(String xmlConfigPath, String contextPath,
			String resourceBase, String webXmlPath, String[] servletPackPaths) {
		this(xmlConfigPath, contextPath, resourceBase, webXmlPath, null, servletPackPaths);
	}

	public JettyCustomServer(String xmlConfigPath, String contextPath, String[] servletPackPaths) {
		this(xmlConfigPath, contextPath, null, null, null, servletPackPaths);
	}

	public JettyCustomServer(String xmlConfigPath, String contextPath,
			String warPath, String[] servletPackPaths) {
		this(xmlConfigPath, contextPath, null, null, warPath, servletPackPaths);
	}

	public JettyCustomServer(String xmlConfigPath, String contextPath,
			String resourceBase, String webXmlPath, String warPath, String[] servletPackPaths) {
		super();
		//this.servletMap = ScanUtils.scanPackage(servletPackPaths, WebServlet.class);
		
		if (StringUtils.isNotBlank(xmlConfigPath)) {
			this.xmlConfigPath = xmlConfigPath;
			readXmlConfig();
		}

		if (StringUtils.isNotBlank(warPath)) {
			this.warPath = warPath;
			if (StringUtils.isNotBlank(contextPath)) {
				this.contextPath = contextPath;
				applyHandle(true);
			}
		} else {
			if (StringUtils.isNotBlank(resourceBase))
				this.resourceBase = resourceBase;
			if (StringUtils.isNotBlank(webXmlPath))
				this.webXmlPath = webXmlPath;
			if (StringUtils.isNotBlank(contextPath)) {
				this.contextPath = contextPath;
				applyHandle(false);
			}
		}
	}
	
	public JettyCustomServer(){super();}
	
	public ConstraintSecurityHandler initServerHttps(ConstraintSecurityHandler security, String xmlConfigPath, String contextPath, String resourceBase, String webXmlPath, String warPath, String[] servletPackPaths) throws IOException{
		
		this.xmlConfigPath = xmlConfigPath;
		this.resourceBase = resourceBase;
		this.webXmlPath = webXmlPath;
		this.contextPath = contextPath;
		if (StringUtils.isNotBlank(xmlConfigPath)) {readXmlConfig();}

		//ContextHandlerCollection handler = new ContextHandlerCollection();

		WebAppContext webapp = initAndGetWebContext();
		security.setHandler(webapp);
		return security;
	}

	private File getJspCompileDir() throws IOException {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File compileDir = new File(tempDir.toString(), "embedded-jetty-jsp");

		if (!compileDir.exists()) {
			if (!compileDir.mkdirs()) {
				throw new IOException("Unable to create compile directory: " + compileDir);
			}
		}
		return compileDir;
	}
//	private List<ContainerInitializer> jspInitializers() {
//		JettyJasperInitializer sci = new JettyJasperInitializer();
//		ContainerInitializer initializer = new ContainerInitializer(sci, null);
//		List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
//		initializers.add(initializer);
//		return initializers;
//	}
	private WebAppContext initAndGetWebContext() throws IOException{
		
		WebAppContext webapp = new WebAppContext();
		//PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
		//webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		
		webapp.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		/*new EmbeddedServletOptions()*/
		//TODO
		/*try {
			webapp.setClassLoader(new WebAppClassLoader(getClass().getClassLoader(), webapp));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		webapp.setInitParameter("org.apache.jasper.compiler.disablejsr199", "false");
		
		webapp.setAttribute("javax.servlet.context.tempdir", getJspCompileDir());
		webapp.setContextPath(contextPath);
		
		webapp.setResourceBase(resourceBase);
		webapp.setDescriptor(webXmlPath);
		webapp.setParentLoaderPriority(true);
		//set max form size 2M
		webapp.setMaxFormContentSize(2097152);
		
		webapp.setConfigurations(new Configuration[] {
				new AnnotationConfiguration(),
				new WebInfConfiguration(),
				new WebXmlConfiguration(),
				new MetaInfConfiguration(),
				new FragmentConfiguration(),
				new EnvConfiguration(),
				new PlusConfiguration(),
				new JettyWebXmlConfiguration()
				});
//		webapp.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializers());
//		webapp.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
		webapp.addBean(new ServletContainerInitializersStarter(webapp), true);
		webapp.setClassLoader(new URLClassLoader(new URL[0], JettyCustomServer.class.getClassLoader()));
		
		webapp.setParentLoaderPriority(true);
		return webapp;
	}
	
	private void readXmlConfig() {
		try {
			XmlConfiguration configuration = new XmlConfiguration(
					new FileInputStream(this.xmlConfigPath));
			configuration.configure(this);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void applyHandle(Boolean warDeployFlag) {

		ContextHandlerCollection handler = new ContextHandlerCollection();

		WebAppContext webapp = new WebAppContext();
		//PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		webapp.setParentLoaderPriority(true);
		//webapp.setInitParameter("org.eclipse.jetty.servlet.DefaultServlet._dirAllowed", "false");
		//webapp.setDefaultsDescriptor("./jetty/etc/webdefault.xml");
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false"); 
		webapp.setContextPath(contextPath);
		
		if (!warDeployFlag) {
			webapp.setResourceBase(resourceBase);
			webapp.setDescriptor(webXmlPath);
		} else {
			webapp.setWar(warPath);
		}
		if(!servletMap.isEmpty()){
			for(Map.Entry<Class<?>, WebServlet> m : servletMap.entrySet()){
				if(m.getValue().value() != null && m.getValue().value().length > 0){
					System.out.println("add servlet by value : " + m.getValue().value()[0]);
					webapp.addServlet((Class<Servlet>)m.getKey(), m.getValue().value()[0]);
				}else{
					System.out.println("add servlet by urlPatterns : " + m.getValue().urlPatterns()[0]);
					webapp.addServlet((Class<Servlet>)m.getKey(), m.getValue().urlPatterns()[0]);
				}
			}	
		}
		handler.addHandler(webapp);
		super.setHandler(handler);
	}

	public void startServer() {
		try {
			super.start();
			System.out.println("current thread:"
					+ super.getThreadPool().getThreads() + "| idle thread:"
					+ super.getThreadPool().getIdleThreads());
			super.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getXmlConfigPath() {
		return xmlConfigPath;
	}

	public void setXmlConfigPath(String xmlConfigPath) {
		this.xmlConfigPath = xmlConfigPath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getResourceBase() {
		return resourceBase;
	}

	public void setResourceBase(String resourceBase) {
		this.resourceBase = resourceBase;
	}

	public String getWebXmlPath() {
		return webXmlPath;
	}

	public void setWebXmlPath(String webXmlPath) {
		this.webXmlPath = webXmlPath;
	}

	public String getWarPath() {
		return warPath;
	}

	public void setWarPath(String warPath) {
		this.warPath = warPath;
	}

}
