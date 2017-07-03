package com.eric.inter.entity;

import java.io.Serializable;

public class UserPay implements Serializable{

	private static final long serialVersionUID = 6246976950116484530L;

	private Integer id;
	private Integer userId;		//支付用户ID
	private Integer payPoint;	//支付额度
	private String payType;		//支付方式
	private String payTime;		//支付时间
	private Integer remainPoint;//用户余额
	private Long payTS;			//支付时间戳
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
	public Integer getPayPoint() {
		return payPoint;
	}
	public void setPayPoint(Integer payPoint) {
		this.payPoint = payPoint;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Integer getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(Integer remainPoint) {
		this.remainPoint = remainPoint;
	}
	public Long getPayTS() {
		return payTS;
	}
	public void setPayTS(Long payTS) {
		this.payTS = payTS;
	}
	
}
