package com.eric.service.bus;

import com.eric.components.Exceptions;

public class AbsController {

	protected void checkParamIsBlank(Object...value) {
		for (Object param : value) {
			if(param==null||"".equals(param)){
				Exceptions.throwException("参数不能为空！");
			}
		}
	}
	
}
