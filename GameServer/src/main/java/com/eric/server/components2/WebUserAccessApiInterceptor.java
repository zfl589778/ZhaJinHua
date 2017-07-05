package com.eric.server.components2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

public class WebUserAccessApiInterceptor extends HandlerInterceptorAdapter {
	
//	private static final Logger logger = LoggerFactory.getLogger(WebUserAccessApiInterceptor.class);
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		
//		response.setContentType("text/html;charset=UTF-8");
//		request.setAttribute("^sys_starttime$", System.currentTimeMillis());
//		
//		//TODO ****** 云测试用，禁止账号互踢
//		//if(1 == 1)return true;
//		HandlerMethod handlerMethod = (HandlerMethod) handler;
//		Method method = handlerMethod.getMethod();
//		
//		Rate rate = method.getAnnotation(Rate.class);
//		if(rate != null){
//			String key = method.getDeclaringClass().getSimpleName() + "-" + method.getName();
//			if(!SpringAnnotationService.getInstance().acquire(key)){
//				writeJson(response, -2, "系统繁忙，请稍候重试"); return false;
//			}
//		}
//		
//		NotNeedSign notNeedSign = method.getAnnotation(NotNeedSign.class);
//		
//		if(notNeedSign == null){
//			String sign = "";
//			try{
//				sign = request.getParameter("sign");
//			}catch(IllegalStateException e){
//				writeJson(response, -1, "上传图片过大");
//				return false;
//				//throw e;
//			}
//			if(StringUtils.isBlank(sign)){writeJson(response, -2, "加密串为空");return false;}
//			String args = getArgs(request);
//			logger.debug("request args: {}, ip: {}", args, IpUtils.getIpAddr(request));
//			if(!checkSign(sign, args, request)){writeJson(response, -1, "秘钥验证错误");return false;}
//		}
//		NotNeedLogin notNeedLogin = method.getAnnotation(NotNeedLogin.class);
//		if (notNeedLogin != null)return true;
//		
//		String sessionid = request.getParameter("sessionid");
//		if(StringUtils.isBlank(sessionid)){writeJson(response, -2, "用户未登陆，会话标识为空"); return false;}
//		CSession cs = CacheFactory.getInstance().getSessionCache().getCSession(sessionid);
//		SessionUser u = cs == null ? null : cs.getUser();
//		
//		if(u == null || u.getUserid() == null){writeJson(response, -2, "用户未登陆"); return false;}
//		
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		
//		long startTime = (Long) request.getAttribute("^sys_starttime$");
//		long curMs = System.currentTimeMillis() - startTime;
//		if(curMs >= 5)LogServer.error(Profiler.class, "url=" + request.getRequestURI() + " :" + curMs);
//	}
//	
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		if(ex != null){
//			HandlerMethod handlerMethod = (HandlerMethod) handler;
//			Method method = handlerMethod.getMethod();
//			String mName = method.getName();
//			
//			response.getWriter().print(ResultObjectUtil.fire2Json(-3, "ERROR:-3"));
//		}
//	 } 
//	 
//	public static void writeJson(HttpServletResponse response, int code, String msg){
//		try {
//			response.getWriter().write(JSON.toJSONString(ResultObjectUtil.fire(code, msg)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static String getArgs(HttpServletRequest request) throws UnsupportedEncodingException{
//		
//		Map<String, String[]> paramMap = request.getParameterMap();
//		Set<String> set = paramMap.keySet();
//		String[] keys = set.toArray(new String[set.size()]);
//		Arrays.sort(keys);
//		String sParams = "";
//		
//		for (String key : keys ){
//			if ( key.equals("sign")) continue;
//			String value = request.getParameter(key);
//			if (StringUtils.isBlank(value)) value = "";
//			if (sParams.length() > 0) sParams += "&";
//			sParams = sParams + key + "=" + value;
//		}
//		return sParams;
//	}
//	
//	private static String appKey = "123456";
//	protected static boolean checkSign(String sign, String builder, HttpServletRequest request) throws UnsupportedEncodingException {
//		appKey = MainConfig.getLocalAppKey();
//		builder += ("&appkey=" + appKey);
//		String mySign = MD5.getMD5(builder);
//		boolean isPass = mySign.equals(sign);
//		
//		if(!isPass){
//			LogServer.error(WebUserAccessApiInterceptor.class, request.getRequestURI() + "   args = " + builder + ", mySign = " + mySign + ", otherSign = " + sign + ", isPass = " + isPass + ", " + ", appKey = " + appKey);
//		}
//		return isPass;
//	}
	
}
