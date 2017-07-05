package com.eric.service.bus.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eric.components.CommonResult;
import com.eric.inter.ILoginService;
import com.eric.inter.entity.db.User;
import com.eric.service.annotation.NotNeedLogin;
import com.eric.service.bus.AbsController;

@Controller
@RequestMapping("/portal")
public class PortalController extends AbsController{

	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="/reg")
	@NotNeedLogin
	@ResponseBody
	public CommonResult<String> regist(String loginName,String password,String nickname,String gender,String devCode){
		if(checkParamIsBlank(loginName,password,nickname,gender)){
			return CommonResult.returnFail("参数不能为空！");
		};
		CommonResult<String> result = loginService.regist(loginName, password, nickname, Integer.parseInt(gender), devCode);
		return result;
	}

	@RequestMapping(value = "/login/normal")
	@NotNeedLogin
	@ResponseBody
	public CommonResult<User> loginNormal(String loginName,String password){
		if(checkParamIsBlank(loginName,password)){
			return CommonResult.returnFail("参数不能为空！");
		};
		CommonResult<User> result = loginService.loginNormal(loginName, password);
		return result;
	}
	
}
