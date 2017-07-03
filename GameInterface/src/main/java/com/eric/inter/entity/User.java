package com.eric.inter.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -4336339964692509152L;

	private Integer id;
	private String loginName;	//登录名
	private String password;	//密码
	private String nickname;	//昵称
	private String avatarUrl;	//头像地址
	private Integer remainPoint;//余额
	private Integer expPoint;	//经验值
	private Integer level;		//等级
	private String title;		//称号
	private Integer status;		//状态：0：正常，1：锁定
	private String devCode;		//设备码，仅支持移动设备
	private Integer isVisitor;	//是否为游客，0：否，1：是，如果为游客以设备码作为唯一标识
	
	private String sessionId;	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Integer getExpPoint() {
		return expPoint;
	}
	public void setExpPoint(Integer expPoint) {
		this.expPoint = expPoint;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public Integer getIsVisitor() {
		return isVisitor;
	}
	public void setIsVisitor(Integer isVisitor) {
		this.isVisitor = isVisitor;
	}
	public Integer getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(Integer remainPoint) {
		this.remainPoint = remainPoint;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
