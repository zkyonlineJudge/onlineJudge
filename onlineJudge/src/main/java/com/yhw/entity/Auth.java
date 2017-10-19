package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * 权限
 */
@Entity
@Table(name="auth")
public class Auth {
	
	
	
	
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 对应的权限名
	 * 暂定无（普通用户）no，全部（管理员）all，题库权限 title,公告权限 post，用户权限 user，评论权限 comment
	 */
	@Column(name="auth")
	private String auth;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	
}
