package com.eric.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class ExceptionHandler implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(ExceptionHandler.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("异常:" + ex.getMessage(), ex);
        return format2View(ex.getMessage());
	}
	
	private ModelAndView format2View(String msg){
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("isSuccess", false);
		mav.addObject("msg", msg);
		return mav;
	}
}
