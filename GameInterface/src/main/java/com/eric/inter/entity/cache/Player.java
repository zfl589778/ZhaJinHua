package com.eric.inter.entity.cache;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	private String sessionId;	//会话ID（PK）
	private Integer position;	//当前位置
	private String nickname;	//昵称
	private Integer gender;		//性别：0男1女
	private String headImage;	//头像
	private Integer level;		//等级
	private String title;		//称号
	private Integer exp;		//经验值
	private Integer levelupExp;	//升级经验
	private Long remainPoint;	//余额
	private Long playerOrder;	//当前投注
	private Integer isLook;		//是否看牌：0否1是
	private Integer isThrow;	//是否弃牌：0否1是
	private Integer isWatching;	//是否观战：0否1是
	private Integer isBanker;	//是否为庄：0否1是
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Integer getLevelupExp() {
		return levelupExp;
	}
	public void setLevelupExp(Integer levelupExp) {
		this.levelupExp = levelupExp;
	}
	public Long getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(Long remainPoint) {
		this.remainPoint = remainPoint;
	}
	public Long getPlayerOrder() {
		return playerOrder;
	}
	public void setPlayerOrder(Long playerOrder) {
		this.playerOrder = playerOrder;
	}
	public Integer getIsLook() {
		return isLook;
	}
	public void setIsLook(Integer isLook) {
		this.isLook = isLook;
	}
	public Integer getIsThrow() {
		return isThrow;
	}
	public void setIsThrow(Integer isThrow) {
		this.isThrow = isThrow;
	}
	public Integer getIsWatching() {
		return isWatching;
	}
	public void setIsWatching(Integer isWatching) {
		this.isWatching = isWatching;
	}
	public Integer getIsBanker() {
		return isBanker;
	}
	public void setIsBanker(Integer isBanker) {
		this.isBanker = isBanker;
	}
	
}
