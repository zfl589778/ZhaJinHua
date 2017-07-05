package com.eric.inter.entity.db;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sessionId;	//会话ID
	private String loginName;	//登录名
	private String password;	//密码
	private String nickname;	//昵称
	private String headImage;	//头像
	private Long remainPoint;	//余额
	private Long remainCash;	//持有现金
	private Integer level;		//等级
	private String title;		//称号
	private Integer exp;		//经验值
	private Integer levelupExp;	//升级经验值
	private Integer gender;		//性别：0男1女
	private Date birthday;		//生日
	private String province;	//省份
	private String city;		//城市
	private Integer totalGameCount;	//总场数
	private Integer winGameCount;	//胜场数
	private String comment;		//个性签名
	private Integer status;		//状态：0：正常，1：锁定
	private String devCode;		//设备码，仅支持移动设备
	private Integer isVisitor;	//是否为游客，0：否，1：是，如果为游客以设备码作为唯一标识
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Long getRemainPoint() {
		return remainPoint;
	}
	public void setRemainPoint(Long remainPoint) {
		this.remainPoint = remainPoint;
	}
	public Long getRemainCash() {
		return remainCash;
	}
	public void setRemainCash(Long remainCash) {
		this.remainCash = remainCash;
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getTotalGameCount() {
		return totalGameCount;
	}
	public void setTotalGameCount(Integer totalGameCount) {
		this.totalGameCount = totalGameCount;
	}
	public Integer getWinGameCount() {
		return winGameCount;
	}
	public void setWinGameCount(Integer winGameCount) {
		this.winGameCount = winGameCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
}
