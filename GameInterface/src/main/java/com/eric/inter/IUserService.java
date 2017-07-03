package com.eric.inter;

import com.eric.components.CommonResult;
import com.eric.inter.entity.User;

public interface IUserService {
	
	CommonResult<Boolean> clearVisitorTask();
	
	CommonResult<User> modifyUser();
}
