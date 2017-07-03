package com.eric.components;

import java.io.Serializable;

public class CommonResult<T> implements Serializable{
	private static final long serialVersionUID = -733322225483287292L;
	private T data;
	private String msg;
	private Boolean isSuccess;
	
	private void success(T data){
		this.data = data;
		this.msg = "操作成功";
		this.isSuccess = true;
	}
	
	private void fail(String error){
		this.data = null;
		this.msg = error;
		this.isSuccess = false;
	}
	public T getData() {
		return data;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public String getMsg() {
		return msg;
	}
	
	public static <T> CommonResult<T> returnSuccess(T data){
		CommonResult<T> ret = new CommonResult<T>();
		ret.success(data);
		return ret;
	}
	
	public static <T> CommonResult<T> returnFail(String error){
		CommonResult<T> ret = new CommonResult<T>();
		ret.fail(error);
		return ret;
	}
}
