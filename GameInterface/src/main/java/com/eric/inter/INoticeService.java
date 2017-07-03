package com.eric.inter;

import com.eric.components.CommonResult;
import com.eric.inter.entity.Notice;

public interface INoticeService {

	CommonResult<Notice> getTopNotice();
}
