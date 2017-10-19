package com.yhw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * 天梯类
 */
@Entity
@Table(name="high_ladder")
public class HighLadder {
	@Id  
    @GeneratedValue 
	private Integer id;
	/*
	 * 分数
	 */
	@Column
	private Double score;
	@Column(name="user_id")
	private Integer userId;
	/*
	 * 语言
	 */
	@Column(name="language_id")
	private Integer languageId;
	/*
	 * 称号
	 */
	@Column(name="title_id")
	private Integer titleId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
