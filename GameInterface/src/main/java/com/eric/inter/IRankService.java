package com.eric.inter;

import java.util.List;

import com.eric.components.CommonResult;
import com.eric.inter.entity.UserRank;

public interface IRankService {

	CommonResult<List<UserRank>> getUserRankList();
}
