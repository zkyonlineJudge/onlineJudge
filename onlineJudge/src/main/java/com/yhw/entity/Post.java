package com.yhw.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 公告类
 */
@Entity
@Table(name="post")
public class Post {
	@Id  
    @GeneratedValue 
	private Integer id;
	@Column(name="language_id")
	private Integer languageId;
	
	/*
	 * 公告内容
	 */
	@Column
	private String content; 
	/*
	 * 公告标题
	 */
	@Column
	private String title;
	/*
	 * 发布人
	 */
	@Column(name = "author_id")
	private Integer authorId;
	/*
	 * 发布时间
	 */
	@Column
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
