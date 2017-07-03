package com.eric.inter.entity;

import java.io.Serializable;

public class Notice implements Serializable{

	private static final long serialVersionUID = -7217231748352743522L;

	private Integer id;
	private String content;		//公告内容
	private String createTime;	//创建时间
	private String createAdmin;	//创建管理员
	private Long createTS;		//创建时间戳
	private Integer isTop;		//是否置顶，0：否，1：是
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}
	public Long getCreateTS() {
		return createTS;
	}
	public void setCreateTS(Long createTS) {
		this.createTS = createTS;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
}
