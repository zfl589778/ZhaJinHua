package com.eric.inter.entity;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable{

	private static final long serialVersionUID = -3437582466846368992L;
	private String id;
	private String number;				//房间号
	private String name;				//房间名称
	private String password;			//房间密码
	private Integer basePoint;			//底注
	private Integer maxOrderLimit;		//单笔最大下注
	private Integer maxRoundLimit;		//最高轮数
	
	private List<User> users;			//该房间的用户
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBasePoint() {
		return basePoint;
	}
	public void setBasePoint(Integer basePoint) {
		this.basePoint = basePoint;
	}
	public Integer getMaxOrderLimit() {
		return maxOrderLimit;
	}
	public void setMaxOrderLimit(Integer maxOrderLimit) {
		this.maxOrderLimit = maxOrderLimit;
	}
	public Integer getMaxRoundLimit() {
		return maxRoundLimit;
	}
	public void setMaxRoundLimit(Integer maxRoundLimit) {
		this.maxRoundLimit = maxRoundLimit;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
