package com.eric.inter.entity;

import java.io.Serializable;

public class Admin implements Serializable{

	private static final long serialVersionUID = -1582024538630240673L;

	private Integer id;
	private String loginName;	//管理员登录名
	private String adminName;	//管理员名
	private String password;	//密码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
