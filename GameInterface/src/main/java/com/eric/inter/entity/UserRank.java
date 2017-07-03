package com.eric.inter.entity;

import java.io.Serializable;

public class UserRank implements Serializable{
	
	private static final long serialVersionUID = -8757513723575257139L;

	private Integer id;
	private String nickname;	//昵称
	private Integer rank;		//排行
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
