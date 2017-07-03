package com.eric.components;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanFactoryUtil implements ApplicationContextAware {
	
	private static Logger logger = LoggerFactory.getLogger(BeanFactoryUtil.class);
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		
		System.out.println("====>> applicationContext init");
		BeanFactoryUtil.applicationContext = applicationContext;
	}
	
	/**
	 * 缓存实例的引用，此处为了
	 */
	private static Map<Class<?>, Object> cacheServices = new HashMap<Class<?>, Object>();
	/**
	 * 得到Bean的实例
	 * 
	 * @param <T>
	 * @param beanClass
	 */
	public static <T extends Object> T getBean(Class<T> beanClass) {
		
		if(applicationContext == null){
			System.out.println("applicationContext is null, please init applicationContext!");
			return null;
		}
		@SuppressWarnings("unchecked")
		T bean = (T) cacheServices.get(beanClass);
		if(bean == null) {
			bean = getBeanFromContext(beanClass);
			cacheServices.put(beanClass, bean);
		}
		return bean;
	}

	/**
	 * 从ApplicationContext中获得Bean对象，由应用抛出异常，得到第一个bean
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	private static <T> T getBeanFromContext(Class<T> beanClass) {
		//得到同类型的所有bean
		Map<String, T> beanMap = applicationContext.getBeansOfType(beanClass);
		Collection<T> beans = beanMap.values();
		if(beans.size() == 0) {
			
		} else if(beans.size() > 1) {
			logger.warn("共找到"+beans.size()+"个"+beanClass+"的实例，系统仅能自动选择其中一个");
		}
		//取Iterator的第一个值。
		T bean = beans.iterator().next();
		return bean;
	}
	
	/**
	 * 获取所有代理bean
	 * @return
	 */
	public static String[] getBeanDefinitionNames(){
		
		return applicationContext.getBeanDefinitionNames();
	}
	
	/**
	 * 获取类型
	 * @param name
	 * @return
	 */
	public static Class<?> getType(String name){
		
		return applicationContext.getType(name);
	}
	
	/**
	   * 根据Bean名称获取实例
	  * @param name Bean注册名称
	  * @return bean实例
	  * @throws BeansException
	   */
	  public static Object getBean(String name) throws BeansException {
		  return applicationContext.getBean(name);
	  }
}
