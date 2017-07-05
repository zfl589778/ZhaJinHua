package com.eric.inter.entity.cache;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer number;		//房间号
	private Integer type;		//游戏类型
	private String name;		//房间名称
	private String password;	//房间密码
	private Long baseOrder;		//底注
	private Long roomOrder;		//目前房间投注
	private Long maxOrder;		//单局投注上限
	private Integer round;		//目前轮数
	private Integer totalRound;	//总轮数
	private Integer count;		//房间人数
	private Integer totalCount;	//总人数
	private Integer isStarted;	//是否已开始：0否1是
	private Integer countDownTimel;	//倒计时时间
	
	private List<Integer> orderList;//台面投注
	private List<Player> playerList;//玩家列表
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Long getBaseOrder() {
		return baseOrder;
	}
	public void setBaseOrder(Long baseOrder) {
		this.baseOrder = baseOrder;
	}
	public Long getRoomOrder() {
		return roomOrder;
	}
	public void setRoomOrder(Long roomOrder) {
		this.roomOrder = roomOrder;
	}
	public Long getMaxOrder() {
		return maxOrder;
	}
	public void setMaxOrder(Long maxOrder) {
		this.maxOrder = maxOrder;
	}
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public Integer getTotalRound() {
		return totalRound;
	}
	public void setTotalRound(Integer totalRound) {
		this.totalRound = totalRound;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getIsStarted() {
		return isStarted;
	}
	public void setIsStarted(Integer isStarted) {
		this.isStarted = isStarted;
	}
	public Integer getCountDownTimel() {
		return countDownTimel;
	}
	public void setCountDownTimel(Integer countDownTimel) {
		this.countDownTimel = countDownTimel;
	}
	public List<Integer> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Integer> orderList) {
		this.orderList = orderList;
	}
	public List<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
}
