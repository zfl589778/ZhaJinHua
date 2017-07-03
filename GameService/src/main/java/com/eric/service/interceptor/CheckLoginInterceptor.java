package com.eric.service.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eric.components.Exceptions;
import com.eric.service.annotation.NotNeedLogin;
import com.eric.service.cache.RedisCacheHelper;
import com.eric.utils.StringUtils;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private RedisCacheHelper redisCacheHelper;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		NotNeedLogin notNeedLogin = method.getAnnotation(NotNeedLogin.class);
		if (notNeedLogin != null)return true;
		String sessionId = request.getParameter("sessionId");
		if(StringUtils.isBlank(sessionId))Exceptions.throwException("会话标识为空");
		Integer userId = redisCacheHelper.get(sessionId);
		if(userId == null)Exceptions.throwException("用户未登录");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	} 
	
}
