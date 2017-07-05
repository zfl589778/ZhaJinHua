package com.eric.server.components2;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

public class SpringAnnotationService {
	
	private static final SpringAnnotationService instance = new SpringAnnotationService();
	public static SpringAnnotationService getInstance(){return instance;}
	
	private Map<String,Object> controllers = null;
	
	private Map<String, RateLimiter> rateLimitMap = Maps.newConcurrentMap();
	
	public void init(Map<String,Object> controllers){
		System.out.println("init all controller...");
		this.controllers = controllers;
		System.out.println("/***************************初始化限流器***************************/");
		for(Map.Entry<String,Object> obj : this.controllers.entrySet()){
			Object val = obj.getValue();
			Method[] ms = val.getClass().getMethods();
			String clsName = val.getClass().getSimpleName();
			for(Method m : ms){
				Rate rate = m.getAnnotation(Rate.class);
				if(rate == null)continue;
				rateLimitMap.put(clsName + "-" + m.getName(), RateLimiter.create(rate.token()*1.0));
				System.out.println("/*******************class-method[" +clsName + "-" + m.getName() + ", " + rate.token() + "]");
			}	
		}
		System.out.println("/****************************************************************/");
	}
	
	public boolean acquire(String methodName) {
		return rateLimitMap.get(methodName).tryAcquire(3, TimeUnit.SECONDS);
	}
}
