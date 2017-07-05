package com.eric.inter;

import com.eric.components.CommonResult;
import com.eric.inter.entity.db.User;

public interface IUserService {
	
	CommonResult<Boolean> clearVisitorTask();
	
	CommonResult<User> modifyUser();
}
