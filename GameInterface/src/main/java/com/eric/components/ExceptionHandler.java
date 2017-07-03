package com.eric.components;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class ExceptionHandler implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(ExceptionHandler.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("异常:" + ex.getMessage(), ex);
        try {
			response.getWriter().write(JSONObject.toJSONString(CommonResult.returnFail(ex.getMessage())));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
}
