package com.eric.inter.entity;

import java.io.Serializable;

public class UserLogin implements Serializable{

	private static final long serialVersionUID = -4660131435131950467L;

	private Integer id;
	private Integer userId;		//登录用户ID
	private String loginTime;	//登录时间
	private String loginIP;		//登录IP
	private Long loginTS;		//登录时间戳
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public Long getLoginTS() {
		return loginTS;
	}
	public void setLoginTS(Long loginTS) {
		this.loginTS = loginTS;
	}
	
}
