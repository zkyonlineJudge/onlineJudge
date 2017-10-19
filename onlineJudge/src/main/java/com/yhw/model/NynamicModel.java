package com.yhw.model;

import java.util.Date;

public class NynamicModel {
	//题目id
	private Integer id;
	private String urlA;
	private String urlB;
	private String title;
	private String content;
	private Date time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrlA() {
		return urlA;
	}
	public void setUrlA(String urlA) {
		this.urlA = urlA;
	}
	public String getUrlB() {
		return urlB;
	}
	public void setUrlB(String urlB) {
		this.urlB = urlB;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}


}
