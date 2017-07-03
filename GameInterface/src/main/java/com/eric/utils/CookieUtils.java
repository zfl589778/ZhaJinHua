package com.eric.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	/**
	 * 添加cookie
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response,String key,String value){
		addCookie(response, key, value, -1, null);
	}
	
	public static void addCookie(HttpServletResponse response,String key,String value,int maxAge){
		addCookie(response, key, value, maxAge, null);
	}
	
	public static void addCookie(HttpServletResponse response,String key,String value,int maxAge,String doMain){
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(maxAge);
		cookie.setDomain(doMain);
		response.addCookie(cookie);
	}
	/**
	 * 获取cookie的jsessionId
	 * @param request
	 * @return
	 */
	public static String getCookieJSessionId(HttpServletRequest request){
		return getCookieValue(request, "JSESSIONID");
	}
	
	/**
	 * 根据key获得cookie中的值
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String key){
		String result = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(key)){
					result = cookie.getValue();
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据key删除cookie
	 * @param request
	 * @param response
	 * @param key
	 */
	public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String key){
		Cookie[] cookies=request.getCookies();       
		if(cookies!=null)       
		{       
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(key)){
					cookies[0].setMaxAge(0);       
					response.addCookie(cookie); 
				}
			}
		}   
	}
}
