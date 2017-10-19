package com.yhw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {
	@Id  
    @GeneratedValue 
	private Integer id;
	
	//用户名
	@Column
	private String username;
	
	@Column
	private String password;
	
	//最近登录时间
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	//注册时间
	@Column(name="register_time")
	private Date registerTime;
	
	//级别
	@Column(name = "rank_id")
	private Integer rankId;
	
	//邮箱
	@Column
	private String email;
	
	//手机号
	@Column
	private String phone;
	
	//个人自述
	@Column(name="dsc")
	private String description;
	
	//天梯信息
	@Column(name="high_ladder_id")
	private Integer highLadderId;
	/*
	 * 权限
	 */
	@Column(name = "auth_id")
	private Integer authId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getRankId() {
		return rankId;
	}
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getHighLadderId() {
		return highLadderId;
	}
	public void setHighLadderId(Integer highLadderId) {
		this.highLadderId = highLadderId;
	}
	public Integer getAuthId() {
		return authId;
	}
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
