package com.eric.inter;

import com.eric.components.CommonResult;
import com.eric.inter.entity.db.User;

public interface ILoginService {

	CommonResult<Boolean> regist(String loginName,String password,String nickname,String avatarUrl,String devCode);
	
	CommonResult<User> loginNormal(String loginName,String password);
	
	CommonResult<User> loginAuto(String devCode);
	
	CommonResult<User> loginVisitor(String devCode);
	
	CommonResult<Boolean> forgotPassword();
	
	CommonResult<User> visitorToUser();

}
