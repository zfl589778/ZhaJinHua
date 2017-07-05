package com.eric.service.bus;

public class AbsController {

	protected boolean checkParamIsBlank(Object...value) {
		for (Object param : value) {
			if(param==null||"".equals(param)){
				return true;
			}
		}
		return false;
	}
	
}
