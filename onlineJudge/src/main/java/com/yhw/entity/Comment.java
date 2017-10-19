package com.yhw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="comment")
public class Comment {
	public static Integer TYPE_QUESTION = 0;
	public static Integer TYPE_CODE = 1;
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 父评论
	 * 
	 */
	@Column(name="parent_id")
	private Integer parentId;
	/*
	 * 评论者
	 */
	@Column(name="user_id")
	private Integer userId;
	/*
	 * 评论内容
	 * 
	 */
	@Column
	private String content;
	/*
	 * 评论时间 2017/11/11 11:11:11
	 */
	@Column
	private Date date;
	/*
	 * type=0则为题目Question评论
	 * type=1则为代码Code评论
	 */
	@Column
	private int type;
	/*
	 * 根据type去对应的表去找id为typeId的内容
	 */
	@Column(name="type_id")
	private Integer typeId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
}
