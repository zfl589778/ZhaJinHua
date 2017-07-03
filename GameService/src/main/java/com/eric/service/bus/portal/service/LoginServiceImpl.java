package com.eric.service.bus.portal.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.components.CommonResult;
import com.eric.components.Exceptions;
import com.eric.inter.ILoginService;
import com.eric.inter.entity.User;
import com.eric.service.cache.RedisCacheHelper;
import com.eric.service.dao.MybatisDaoInstance;
import com.eric.utils.MD5Utils;
import com.google.common.collect.Maps;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private RedisCacheHelper redisCacheHelper;

	@Override
	public CommonResult<Boolean> regist(String loginName, String password,
			String nickname, String avatarUrl, String devCode) {
		User u = new User();
		u.setLoginName(loginName);
		u.setPassword(MD5Utils.MD5(password));
		u.setNickname(nickname);
		u.setAvatarUrl(avatarUrl);
		u.setRemainPoint(0);
		u.setExpPoint(0);
		u.setLevel(1);
		u.setTitle("");
		u.setStatus(0);
		u.setDevCode(devCode);
		u.setIsVisitor(0);
		MybatisDaoInstance.getInstance(User.class).insert("insertUser", u);
		return CommonResult.returnSuccess(true);
	}

	@Override
	public CommonResult<User> loginNormal(String loginName,
			String password) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("loginName", loginName);
		params.put("password", MD5Utils.MD5(password));
		List<User> u_list = MybatisDaoInstance.getInstance(User.class).find("getUserList", params);
		if (u_list == null || u_list.isEmpty())
			Exceptions.throwException("用户名或密码错误");
		User u = u_list.get(0);
		if (u == null)
			Exceptions.throwException("找不到该用户");
		if (u.getStatus() == 1)
			Exceptions.throwException("该用户已被锁定");
		String sessionId = UUID.randomUUID().toString();
		u.setSessionId(sessionId);
		redisCacheHelper.put(sessionId, u);
		return CommonResult.returnSuccess(u);
	}

	@Override
	public CommonResult<User> loginAuto(String devCode) {
		return CommonResult.returnSuccess(null);
	}

	@Override
	public CommonResult<User> loginVisitor(String devCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<Boolean> forgotPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResult<User> visitorToUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
