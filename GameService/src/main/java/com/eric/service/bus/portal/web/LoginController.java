package com.eric.service.bus.portal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eric.components.CommonResult;
import com.eric.inter.ILoginService;
import com.eric.inter.entity.User;
import com.eric.service.annotation.NotNeedLogin;

@Controller
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="/reg")
	@NotNeedLogin
	@ResponseBody
	public CommonResult<Boolean> regist(HttpServletRequest request){
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String avatarUrl = request.getParameter("avatarUrl");
		String devCode = request.getParameter("devCode");
		CommonResult<Boolean> result = loginService.regist(loginName, password, nickname, avatarUrl,devCode);
		return result;
	}
	
	@RequestMapping(value = "/login/normal")
	@NotNeedLogin
	@ResponseBody
	public CommonResult<User> loginNormal(String loginName,String password){
		CommonResult<User> result = loginService.loginNormal(loginName, password);
		return result;
	}
	
}
