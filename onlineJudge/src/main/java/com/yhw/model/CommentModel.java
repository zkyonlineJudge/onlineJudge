package com.yhw.model;

import java.util.Date;

import com.yhw.entity.Comment;

public class CommentModel {
	private Integer id;
	
	private String author;
	
	private String imagePath;
	
	private Integer parentId;
	
	private String content;
	
	private String date;
	
	private Boolean self;
	
	private String title; //称号
	
	public CommentModel() {
		// TODO Auto-generated constructor stub
		self = false;
		
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Boolean getSelf() {
		return self;
	}

	public void setSelf(Boolean self) {
		this.self = self;
	}
	
	
}
