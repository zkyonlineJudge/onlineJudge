package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class Setting {
	@Id  
    @GeneratedValue 
	private Integer id;
	
	/*
	 * 默认为false 允许别人查看信息
	 * 
	 */
	@Column(name="forbid_other_watch")
	private Boolean forbidOtherWatch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getForbidOtherWatch() {
		return forbidOtherWatch;
	}

	public void setForbidOtherWatch(Boolean forbidOtherWatch) {
		this.forbidOtherWatch = forbidOtherWatch;
	}
	
}
